/**
 * 
 */
package assgn.stock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import assgn.stock.dto.StockDTO;
import assgn.stock.entity.Stock;
import assgn.stock.entity.User;
import assgn.stock.exception.HotStockException;
import assgn.stock.repository.IStockRepository;
import assgn.stock.repository.IUserRepository;
import assgn.stock.utils.MessageByLocaleUtils;

/**
 * @author Jogireddy Kotam
 *
 */
@Service
@Transactional
public class StockServiceImpl implements IStockService {
    
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private MessageByLocaleUtils messageUtils;
    
    @Autowired
    private IStockRepository stockRepository;
    
    @Autowired
    private Mapper mapper;

    @Override
    public StockDTO saveOrUpdateStock(StockDTO stockDTO) throws HotStockException {
        logger.info("Entered saveOrUpdateStock");
        try {
            Stock stock = mapper.map(stockDTO, Stock.class);
            
            User addedBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            if(addedBy == null) {
                throw new HotStockException("Please re-login and try again.");
            }
            
            stock.setAddedBy(addedBy);
            stock.setAddedTs(new Date());
            stock.setLastTradedPrice(stock.getBasePrice());
            stockRepository.save(stock);
            return mapper.map(stock, StockDTO.class);
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public List<StockDTO> getAll() throws HotStockException {
        logger.info("Entered getAll");
        try {
            List<Stock> stocks = stockRepository.findAll();
            List<StockDTO> stockDTOs = new ArrayList<>();
            
            for(Stock stock: stocks) {
                stockDTOs.add(mapper.map(stock, StockDTO.class));
            }
            return stockDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public List<StockDTO> getAllActiveStocks() throws HotStockException {
        logger.info("Entered getAllActiveStocks");
        try {
            List<Stock> stocks = stockRepository.findAllActive(SecurityContextHolder.getContext().getAuthentication().getName());
            List<StockDTO> stockDTOs = new ArrayList<>();
            
            for(Stock stock: stocks) {
                stockDTOs.add(mapper.map(stock, StockDTO.class));
            }
            return stockDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }
    
    @PreAuthorize("hasRole('ROLE_BROKER')")
    @Override
    public List<StockDTO> getLoggedInUserStocks() throws HotStockException {
        logger.info("Entered getStocks");
        try {
            User owner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            if(owner == null) {
                throw new HotStockException("Invalid User Found.");
            }
            List<Stock> stocks = stockRepository.findByOwner(owner);
            List<StockDTO> stockDTOs = new ArrayList<>();
            
            for(Stock stock: stocks) {
                stockDTOs.add(mapper.map(stock, StockDTO.class));
            }
            return stockDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public boolean removeStock(Long stockId) throws HotStockException {
        logger.info("Entered transferStock");
        try {
            stockRepository.delete(stockId);
            return true;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public boolean transferStock(Long stockId, Long userId) throws HotStockException {
        logger.info("Entered transferStock");
        try {
            Stock stock = stockRepository.findOne(stockId);
            if(stock == null) {
                logger.info("Stock not found with id: " + stockId);
                throw new HotStockException("Stock Not Found for Id: " + stockId);
            }
            User user = userRepository.findOne(userId);
            
            if(user == null) {
                logger.info("User not found with id: " + userId);
                throw new HotStockException("User Not Found for Id: " + userId);
            }
            
            User transferredBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            stock.setTransferredBy(transferredBy);
            stock.setOwner(user);
            stock.setTransferredTs(new Date());
            stock.setLastTradedPrice(stock.getBasePrice());
            stock.setActive(true);
            stockRepository.save(stock);
            return true;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

}
