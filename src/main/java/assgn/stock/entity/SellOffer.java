/**
 * 
 */
package assgn.stock.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Jogireddy Koatm
 *
 */
@Entity
@Table(name = "sell_offers")
public class SellOffer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3891869631598429634L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
    
    @Column(name = "price", nullable = false)
    private Double price;
    
    @Column(name = "accepted", nullable = false)
    private boolean accepted;
    
    @Column(name = "declined", nullable = false)
    private boolean declined;
    
    @Column(name = "qty", nullable = false)
    private Integer quantity;

    
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
    
    public Integer getQuantity() {
        return quantity;
    }


    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }    

}
