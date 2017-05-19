/**
 * 
 */
package assgn.stock.service;

import java.util.List;

import assgn.stock.dto.BuyIntentDTO;
import assgn.stock.dto.BuySellRequestDTO;
import assgn.stock.dto.SellOfferDTO;
import assgn.stock.dto.TradeDTO;
import assgn.stock.exception.HotStockException;

/**
 * @author Jogireddy Kotam
 *
 */
public interface ITradeService {
    
    public List<TradeDTO> todayTrades() throws HotStockException;
    
    public List<BuyIntentDTO> getMyBuyerIntentStocks() throws HotStockException;
    
    public List<SellOfferDTO> getMySellerOfferStocks() throws HotStockException;
    
    public boolean buyStockRequest(BuySellRequestDTO buySellRequestDTO) throws HotStockException;
    
    public boolean acceptBuyRequest(Long buyIntentId) throws HotStockException;
    
    public boolean declineBuyRequest(Long buyIntentId) throws HotStockException;
    
    public boolean sellStockRequest(BuySellRequestDTO buySellRequestDTO) throws HotStockException;
    
    public boolean acceptSellRequest(Long sellOfferId) throws HotStockException;
    
    public boolean declineSellRequest(Long sellOfferId) throws HotStockException;
}
