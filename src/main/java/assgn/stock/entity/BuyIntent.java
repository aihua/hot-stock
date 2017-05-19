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
 * @author Jogireddy Kotam
 *
 */
@Entity
@Table(name = "buy_intents")
public class BuyIntent implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4717413531658385537L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
    
    @Column(name = "price", nullable = false)
    private Double price;
    
    @Column(name = "qty", nullable = false)
    private Integer qunantity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "req_user_id", nullable = false)
    private User requestedBy;
    
    @Column(name = "is_acceepted", nullable = false)
    private boolean accepted;
    
    @Column(name = "declined", nullable = false)
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
