/**
 * 
 */
package assgn.stock.dto;

import java.io.Serializable;
import java.util.Date;

import assgn.stock.entity.Stock;
import assgn.stock.entity.User;

/**
 * @author JOgireddy Kotam
 *
 */
public class TradeDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8468197537974349998L;
    
    private Long id;
    
    private Stock stock;
    
    private Integer quantity;
    
    private User buyer;
    
    private User seller;
    
    private Double price;
    
    private Date txTs;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public Stock getStock() {
        return stock;
    }

    
    public void setStock(Stock stock) {
        this.stock = stock;
    }

    
    public Integer getQuantity() {
        return quantity;
    }

    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    public User getBuyer() {
        return buyer;
    }

    
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    
    public User getSeller() {
        return seller;
    }

    
    public void setSeller(User seller) {
        this.seller = seller;
    }

    
    public Double getPrice() {
        return price;
    }

    
    public void setPrice(Double price) {
        this.price = price;
    }

    
    public Date getTxTs() {
        return txTs;
    }

    
    public void setTx_ts(Date txTs) {
        this.txTs = txTs;
    }

}
