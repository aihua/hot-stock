/**
 * 
 */
package assgn.stock.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Jogireddy Kotam
 *
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7773036099868246867L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "symbol", nullable = true)
    private String symbol;
    
    @Column(name = "base_price", nullable = false)
    private Double basePrice;
    
    @Column(name = "last_traded_price", nullable = false)
    private Double lastTradedPrice;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by", nullable = false)
    private User addedBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_ts", nullable = false)
    private Date addedTs;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transferred_by", nullable = true)
    private User transferredBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transferred_ts", nullable = true)
    private Date transferredTs;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = true)
    private User owner;
    
    @Column(name = "is_active", nullable = false)
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


    
    public String getSymbol() {
        return symbol;
    }


    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public boolean isActive() {
        return active;
    }


    
    public void setActive(boolean active) {
        this.active = active;
    }  
    
    
    
}
