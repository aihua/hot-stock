/**
 * 
 */
package assgn.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import assgn.stock.entity.SellOffer;
import assgn.stock.entity.User;

/**
 * @author Jogireddy Kotam
 *
 */
@Repository
public interface ISellOfferRepository extends JpaRepository<SellOffer, Long> {
    
    @Query("SELECT sellOffer FROM SellOffer sellOffer WHERE sellOffer.accepted = false AND sellOffer.stock.owner != ?1")
    public List<SellOffer> findActiveSellerOffersByStockOwners(User owner);

}
