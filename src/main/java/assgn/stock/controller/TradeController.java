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

import assgn.stock.dto.BuySellRequestDTO;
import assgn.stock.exception.HotStockException;
import assgn.stock.service.ITradeService;
import assgn.stock.utils.String2MapUtil;

/**
 * @author Jogireddy Kotam
 *
 */
@RestController
@RequestMapping("/trades")
public class TradeController {
    
    private static final Logger logger = LogManager.getLogger(TradeController.class);
    
    @Autowired
    private ITradeService tradeService;
    
    @RequestMapping(value = "/today", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> todayTrades() {
        logger.info("Entered todayTrades");
        try {
            return new ResponseEntity<>(tradeService.todayTrades(), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/buy-request", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> buyStockRequest(@RequestBody BuySellRequestDTO buySellRequestDTO) {
        logger.info("Entered deleteStock");
        try {
            tradeService.buyStockRequest(buySellRequestDTO);
            return new ResponseEntity<>(String2MapUtil.convert("message", "Request sent Successfully."), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/buy/accept/{buyIntentId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> acceptBuyRequest(@PathVariable(name = "buyIntentId", required = true)Long buyIntentId) {
        logger.info("Entered  acceptBuyRequest with intentId: " + buyIntentId);
        try {
            tradeService.acceptBuyRequest(buyIntentId);
            return new ResponseEntity<>(String2MapUtil.convert("message", "Action was Successful.."), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/buy/decline/{buyIntentId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> declineBuyRequest(@PathVariable(name = "buyIntentId", required = true)Long buyIntentId) {
        logger.info("Entered  acceptBuyRequest with intentId: " + buyIntentId);
        try {
            tradeService.declineBuyRequest(buyIntentId);
            return new ResponseEntity<>(String2MapUtil.convert("message", "Action was Successful.."), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/buy/intents", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> activeBuyRequests() {
        logger.info("Entered  activeBuyRequests");
        try {
            return new ResponseEntity<>(tradeService.getMyBuyerIntentStocks(), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/sell/intents", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> activeSellRequests() {
        logger.info("Entered  activeSellRequests");
        try {
            return new ResponseEntity<>(tradeService.getMySellerOfferStocks(), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/sell-offer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> sellStockOffer(@RequestBody BuySellRequestDTO buySellRequestDTO) {
        logger.info("Entered sellStockOffer");
        try {
            tradeService.sellStockRequest(buySellRequestDTO);
            return new ResponseEntity<>(String2MapUtil.convert("message", "Request sent Successfully."), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/sell/accept/{sellIntentId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> acceptSellRequest(@PathVariable(name = "sellIntentId", required = true)Long sellIntentId) {
        logger.info("Entered  acceptBuyRequest with offerId: " + sellIntentId);
        try {
            tradeService.acceptSellRequest(sellIntentId);
            return new ResponseEntity<>(String2MapUtil.convert("message", "Action was Successful.."), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/sell/decline/{sellIntentId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> declineSellRequest(@PathVariable(name = "sellIntentId", required = true)Long sellIntentId) {
        logger.info("Entered  declineSellRequest with offerId: " + sellIntentId);
        try {
            tradeService.declineSellRequest(sellIntentId);
            return new ResponseEntity<>(String2MapUtil.convert("message", "Action was Successful.."), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
