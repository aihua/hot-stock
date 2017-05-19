/**
 * 
 */
package assgn.stock.dto;

import java.io.Serializable;
import java.util.Date;

import assgn.stock.entity.User;

/**
 * @author Jogireddy Kotam
 *
 */
public class StockDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4317677765061777804L;
    
    private Long id;
    
    private String name;
    
    private String symbol;
    
    private Double basePrice;
    
    private Double lastTradedPrice;
    
    private User addedBy;
    
    private Date addedTs;
    
    private User transferredBy;
    
    private Date transferredTs;
    
    private User owner;
    
    private boolean active;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    

    
    public String getSymbol() {
        return symbol;
    }


    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public Double getBasePrice() {
        return basePrice;
    }

    
    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }
    
    public Double getLastTradedPrice() {
        return lastTradedPrice;
    }

    
    public void setLastTradedPrice(Double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    
    public User getAddedBy() {
        return addedBy;
    }

    
    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    
    public Date getAddedTs() {
        return addedTs;
    }

    
    public void setAddedTs(Date addedTs) {
        this.addedTs = addedTs;
    }

    
    public User getTransferredBy() {
        return transferredBy;
    }

    
    public void setTransferredBy(User transferredBy) {
        this.transferredBy = transferredBy;
    }

    
    public Date getTransferredTs() {
        return transferredTs;
    }

    
    public void setTransferredTs(Date transferredTs) {
        this.transferredTs = transferredTs;
    }

    
    public User getOwner() {
        return owner;
    }

    
    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public boolean isActive() {
        return active;
    }


    
    public void setActive(boolean active) {
        this.active = active;
    }  
    

}
