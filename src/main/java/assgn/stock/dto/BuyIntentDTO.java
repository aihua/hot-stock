package assgn.stock.dto;

import java.io.Serializable;

import assgn.stock.entity.Stock;
import assgn.stock.entity.User;

public class BuyIntentDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6270845087869689823L;
    
    private Long id;
    
    private Stock stock;
    
    private Double price;
    
    private Integer qunantity;
    
    private User requestedBy;
    
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

    
    public Integer getQunantity() {
        return qunantity;
    }

    
    public void setQunantity(Integer qunantity) {
        this.qunantity = qunantity;
    }

    
    public User getRequestedBy() {
        return requestedBy;
    }

    
    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
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
