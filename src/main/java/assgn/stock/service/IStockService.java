/**
 * 
 */
package assgn.stock.service;

import java.util.List;

import assgn.stock.dto.StockDTO;
import assgn.stock.exception.HotStockException;

/**
 * @author Jogireddy Kotam
 *
 */
public interface IStockService {
    
    public StockDTO saveOrUpdateStock(StockDTO stockDTO) throws HotStockException;
    
    public List<StockDTO> getAll() throws HotStockException;
    
    public List<StockDTO> getAllActiveStocks() throws HotStockException;
    
    public List<StockDTO> getLoggedInUserStocks() throws HotStockException;
    
    public boolean removeStock(Long stockId) throws HotStockException;
    
    public boolean transferStock(Long stockId, Long userId) throws HotStockException;

}
