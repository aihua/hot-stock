/**
 * 
 */
package assgn.stock.dto;

import java.io.Serializable;

import assgn.stock.entity.Stock;

/**
 * @author Jogireddy Kotam
 *
 */
public class SellOfferDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1862230630029530901L;
    
    private Long id;
    
    private Stock stock;
    
    private Double price;
    
    private boolean accepted;
    
    private boolean declined;

    
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

    
    public Double getPrice() {
        return price;
    }

    
    public void setPrice(Double price) {
        this.price = price;
    }   
    
    public boolean isAccepted() {
        return accepted;
    }


    
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    
    public boolean isDeclined() {
        return declined;
    }


    
    public void setDeclined(boolean declined) {
        this.declined = declined;
    }

}
