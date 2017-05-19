/**
 * 
 */
package assgn.stock.dto;

import java.io.Serializable;

/**
 * @author Jogireddy Kotam
 *
 */
public class BuySellRequestDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -430398023236634413L;
    
    private Long stockId;
    
    private Double price;

    
    public Long getStockId() {
        return stockId;
    }

    
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    
    public Double getPrice() {
        return price;
    }

    
    public void setPrice(Double price) {
        this.price = price;
    }

}
