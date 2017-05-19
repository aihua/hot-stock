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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import assgn.stock.dto.BuyIntentDTO;
import assgn.stock.dto.BuySellRequestDTO;
import assgn.stock.dto.SellOfferDTO;
import assgn.stock.dto.TradeDTO;
import assgn.stock.entity.BuyIntent;
import assgn.stock.entity.SellOffer;
import assgn.stock.entity.Stock;
import assgn.stock.entity.Trade;
import assgn.stock.entity.User;
import assgn.stock.exception.HotStockException;
import assgn.stock.repository.IBuyIntentRepository;
import assgn.stock.repository.ISellOfferRepository;
import assgn.stock.repository.IStockRepository;
import assgn.stock.repository.ITradeRepository;
import assgn.stock.repository.IUserRepository;
import assgn.stock.utils.MessageByLocaleUtils;

/**
 * @author Jogireddy Kotam
 *
 */
@Service
@Transactional
public class TradeServiceImpl implements ITradeService {
    
    private static final Logger logger = LogManager.getLogger(TradeServiceImpl.class);
    
    @Autowired
    private ITradeRepository tradeRepository;
    
    @Autowired
    private IStockRepository stockRepository;
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private IBuyIntentRepository buyIntentRepository;
    
    @Autowired
    private ISellOfferRepository sellOfferRepository;
    
    @Autowired
    private MessageByLocaleUtils messageUtils;
    
    @Autowired
    private Mapper mapper;

    @Override
    public List<TradeDTO> todayTrades() throws HotStockException {
        logger.info("Entered todayTrades");
        try {
            List<Trade> trades = tradeRepository.findByTxTsOrderByTxTs(new Date());
            List<TradeDTO> tradeDTOs = new ArrayList<>();
            for(Trade trade: trades) {
                tradeDTOs.add(mapper.map(trade, TradeDTO.class));
            }
            return tradeDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }
    
    @Override
    public boolean buyStockRequest(BuySellRequestDTO buySellRequestDTO) throws HotStockException {
        logger.info("Entered buyStockRequest");
        try {
            Stock stock = stockRepository.findOne(buySellRequestDTO.getStockId());
            if(stock == null) {
                throw new HotStockException("Invalid Stock!");
            }
            
            User requestedBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            if(requestedBy == null) {
                throw new HotStockException("Invalid User Found!");
            }
            BuyIntent buyIntent = new BuyIntent();
            buyIntent.setStock(stock);
            buyIntent.setPrice(buySellRequestDTO.getPrice());
            buyIntent.setRequestedBy(requestedBy);
            buyIntent.setQunantity(1);
            buyIntentRepository.save(buyIntent);
            return true;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(HotStockException hotStockException){
            throw hotStockException;
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public boolean acceptBuyRequest(Long buyIntentId) throws HotStockException {
        logger.info("Entered acceptBuyRequest");
        try {
            BuyIntent buyIntent = buyIntentRepository.findOne(buyIntentId);
            if(buyIntent == null) {
                throw new HotStockException("Invalid Intent. Unable to Process request.");
            }
            
            
            Stock stock = buyIntent.getStock();
            User prevOwner = stock.getOwner();
            stock.setLastTradedPrice(buyIntent.getPrice());
            stock.setOwner(buyIntent.getRequestedBy());
            stock.setTransferredBy(prevOwner);
            stock.setTransferredTs(new Date());
            stockRepository.save(stock);
            
            Trade trade = new Trade();
            trade.setStock(stock);
            trade.setBuyer(stock.getOwner());
            trade.setSeller(prevOwner);
            trade.setPrice(stock.getLastTradedPrice());
            trade.setQuantity(buyIntent.getQunantity());
            trade.setTxTs(new Date());
            tradeRepository.save(trade);
            return true;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(HotStockException hotStockException){
            throw hotStockException;
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }
    
    @Override
    public boolean declineBuyRequest(Long buyIntentId) throws HotStockException {
        logger.info("Entered acceptBuyRequest");
        try {
            BuyIntent buyIntent = buyIntentRepository.findOne(buyIntentId);
            if(buyIntent == null) {
                throw new HotStockException("Invalid Intent. Unable to Process request.");
            }
            
            
            buyIntent.setDeclined(true);
            buyIntentRepository.save(buyIntent);
            return true;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(HotStockException hotStockException){
            throw hotStockException;
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public List<BuyIntentDTO> getMyBuyerIntentStocks() throws HotStockException {
        logger.info("Entered getMyBuyerIntentStocks");
        try {
            User owner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            if(owner == null) {
                throw new HotStockException(messageUtils.getMessage("common.fetch.error"));
            }
            List<BuyIntent> buyIntents = buyIntentRepository.findActiveBuyIntentsByStockOwners(owner);
            List<BuyIntentDTO> buyIntentDTOs = new ArrayList<>();
            
            for(BuyIntent buyIntent: buyIntents) {
                buyIntentDTOs.add(mapper.map(buyIntent, BuyIntentDTO.class));
            }
            return buyIntentDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(HotStockException hotStockException){
            throw hotStockException;
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }
    
    @Override
    public List<SellOfferDTO> getMySellerOfferStocks() throws HotStockException {
        logger.info("Entered getMyBuyerIntentStocks");
        try {
            User owner = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            if(owner == null) {
                throw new HotStockException(messageUtils.getMessage("common.fetch.error"));
            }
            List<SellOffer> sellOffers = sellOfferRepository.findActiveSellerOffersByStockOwners(owner);
            List<SellOfferDTO> sellOfferDTOs = new ArrayList<>();
            
            for(SellOffer sellOffer: sellOffers) {
                sellOfferDTOs.add(mapper.map(sellOffer, SellOfferDTO.class));
            }
            return sellOfferDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(HotStockException hotStockException){
            throw hotStockException;
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public boolean sellStockRequest(BuySellRequestDTO buySellRequestDTO) throws HotStockException {
        logger.info("Entered getMyBuyerIntentStocks");
        try {
            Stock stock = stockRepository.findOne(buySellRequestDTO.getStockId());
            if(stock == null) {
                throw new HotStockException("Invalid Stock.");
            }
            
            SellOffer sellOffer = new SellOffer();
            sellOffer.setStock(stock);
            sellOffer.setPrice(buySellRequestDTO.getPrice());
            sellOffer.setQuantity(1);
            sellOfferRepository.save(sellOffer);
            return true;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(HotStockException hotStockException){
            throw hotStockException;
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public boolean acceptSellRequest(Long sellOfferId) throws HotStockException {
        try {
            SellOffer sellOffer = sellOfferRepository.findOne(sellOfferId);
            if(sellOffer == null) {
                throw new HotStockException("Invalid Sell Offer. Unable to Process request.");
            }
            User requestedBy = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            
            Stock stock = sellOffer.getStock();
            User prevOwner = stock.getOwner();
            stock.setLastTradedPrice(sellOffer.getPrice());
            stock.setOwner(requestedBy);
            stock.setTransferredBy(prevOwner);
            stock.setTransferredTs(new Date());
            stockRepository.save(stock);
            
            Trade trade = new Trade();
            trade.setStock(stock);
            trade.setBuyer(stock.getOwner());
            trade.setSeller(prevOwner);
            trade.setPrice(stock.getLastTradedPrice());
            trade.setQuantity(sellOffer.getQuantity());
            trade.setTxTs(new Date());
            tradeRepository.save(trade);
            
            sellOffer.setAccepted(true);
            return true;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(HotStockException hotStockException){
            throw hotStockException;
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public boolean declineSellRequest(Long sellOfferId) throws HotStockException {
        try {
           SellOffer sellOffer = sellOfferRepository.findOne(sellOfferId);
           if(sellOffer == null) {
               throw new HotStockException("Invalid SellOffer");
           }
           
           sellOffer.setDeclined(true);
           return true;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(HotStockException hotStockException){
            throw hotStockException;
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }


}
