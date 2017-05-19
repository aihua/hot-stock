/**
 * 
 */
package assgn.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import assgn.stock.entity.Stock;
import assgn.stock.entity.User;

/**
 * @author Jogireddy Kotam
 *
 */
@Repository
public interface IStockRepository extends JpaRepository<Stock, Long> {
    
    public List<Stock> findByOwner(User owner);
    
    @Query("SELECT stock FROM Stock stock WHERE stock.active = true AND stock.owner.username != ?1")
    public List<Stock> findAllActive(String username);
}
