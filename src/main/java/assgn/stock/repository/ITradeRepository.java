/**
 * 
 */
package assgn.stock.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import assgn.stock.entity.Trade;


/**
 * @author Jogireddy Kotam
 *
 */
@Repository
public interface ITradeRepository extends JpaRepository<Trade, Long> {
    
    @Query("SELECT trade FROM Trade trade WHERE CAST(trade.txTs as date) = CAST(?1 as date)")
    public List<Trade> findByTxTsOrderByTxTs(Date date);

}
