/**
 * 
 */
package assgn.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assgn.stock.entity.User;
import assgn.stock.utils.Role;


/**
 * @author Jogireddy Kotam
 *
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    
    public User findByUsername(String username);
    
    public List<User> findByRoleAndUsernameContaining(Role role, String username);

}
