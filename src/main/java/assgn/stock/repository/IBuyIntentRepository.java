/**
 * 
 */
package assgn.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import assgn.stock.entity.BuyIntent;
import assgn.stock.entity.User;


/**
 * @author Jogireddy Kotam
 *
 */
public interface IBuyIntentRepository extends JpaRepository<BuyIntent, Long> {
    
    @Query("SELECT buyIntent FROM BuyIntent buyIntent WHERE buyIntent.accepted = false")
    public List<BuyIntent> findActiveBuyIntents();
    
    @Query("SELECT buyIntent FROM BuyIntent buyIntent WHERE buyIntent.accepted = false AND buyIntent.stock.owner = ?1 AND buyIntent.requestedBy != ?1")
    public List<BuyIntent> findActiveBuyIntentsByStockOwners(User owner);

}
