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
@Table(name = "trades")
public class Trade implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4226502540543320464L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
    
    @Column(name = "qty", nullable = false)
    private Integer quantity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer", nullable = false)
    private User buyer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller", nullable = false)
    private User seller;
    
    @Column(name = "price", nullable = false)
    private Double price;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tx_ts", nullable = false)
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

    
    public void setTxTs(Date txTs) {
        this.txTs = txTs;
    }

}
