/**
 * 
 */
package assgn.stock.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import assgn.stock.dto.StockDTO;
import assgn.stock.exception.HotStockException;
import assgn.stock.service.IStockService;
import assgn.stock.utils.String2MapUtil;

/**
 * @author Jogireddy Kotam
 *
 */
@RestController
@RequestMapping("/stocks")
public class StockController {
    
    private static final Logger logger = LogManager.getLogger(StockController.class);
    
    @Autowired
    private IStockService stockService;
    
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> saveStock(@RequestBody StockDTO stockDTO) {
        logger.info("Entered saveStock");
        try {
            return new ResponseEntity<>(stockService.saveOrUpdateStock(stockDTO), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getAll() {
        logger.info("Entered getAll");
        try {
            return new ResponseEntity<>(stockService.getAll(), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/all/active", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getAllActiveStocks() {
        logger.info("Entered getAllActiveStocks");
        try {
            return new ResponseEntity<>(stockService.getAllActiveStocks(), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getStocks() {
        logger.info("Entered getAll");
        try {
            return new ResponseEntity<>(stockService.getLoggedInUserStocks(), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/transfer/{stockId}/{transferTo}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> transferStock(@PathVariable(name = "stockId", required = true)Long stockId, 
            @PathVariable(name = "transferTo", required = true)Long transferTo) {
        logger.info("Entered getAll");
        try {
            stockService.transferStock(stockId, transferTo);
            return new ResponseEntity<>(String2MapUtil.convert("message", "Stock was Successfully Transferred."), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @RequestMapping(value = "/{stockId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Object> deleteStock(@PathVariable(name = "stockId", required = true)Long stockId) {
        logger.info("Entered deleteStock");
        try {
            stockService.removeStock(stockId);
            return new ResponseEntity<>(String2MapUtil.convert("message", "Stock was Successfully Removed."), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}
