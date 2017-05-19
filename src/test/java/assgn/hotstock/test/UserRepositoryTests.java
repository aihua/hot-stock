/**
 * 
 */
package assgn.hotstock.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import assgn.stock.HotStockCoreApplication;
import assgn.stock.entity.User;
import assgn.stock.repository.IUserRepository;

/**
 * @author Jogireddy Kotam
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotStockCoreApplication.class)
public class UserRepositoryTests {
    
    @Autowired
    private IUserRepository userRepository;
    
    @Test
    public void testByUsername() {        
        User user1 = userRepository.findByUsername("admin");
        assertEquals("admin", user1.getUsername());
    }

}
