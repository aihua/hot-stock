/**
 * 
 */
package assgn.stock.service;

import java.util.List;

import assgn.stock.dto.UserDTO;
import assgn.stock.exception.HotStockException;
import assgn.stock.utils.Role;

/**
 * @author Jogireddy Kotam
 *
 */
public interface IUserService {
    
    public UserDTO saveBroker(UserDTO userDTO) throws HotStockException;
    
    public UserDTO findByUsername(String username) throws HotStockException;
    
    public List<UserDTO> findUsersByRoleAndUsername(Role role, String username) throws HotStockException;
    
}
