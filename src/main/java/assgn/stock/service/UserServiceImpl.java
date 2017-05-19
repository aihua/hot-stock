/**
 * 
 */
package assgn.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import assgn.stock.dto.UserDTO;
import assgn.stock.entity.User;
import assgn.stock.exception.HotStockException;
import assgn.stock.repository.IUserRepository;
import assgn.stock.utils.MessageByLocaleUtils;
import assgn.stock.utils.Role;


/**
 * @author Jogireddy Kotam
 *
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private MessageByLocaleUtils messageUtils;
    
    @Autowired
    private Mapper mapper;
    
    @Override
    public UserDTO findByUsername(String username) throws HotStockException {
        logger.info("Entered findByUsername with username: " + username);
        try {
            User user = userRepository.findByUsername(username);
            if(user == null) {
                throw new HotStockException("User not found."); 
            }
            return mapper.map(user, UserDTO.class);
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public List<UserDTO> findUsersByRoleAndUsername(Role role, String username) throws HotStockException {
        logger.info("Entered findUsersByRoleAndUsername with Role: " + role + "username: " + username);
        try {
            List<User> users = userRepository.findByRoleAndUsernameContaining(role, username);
            List<UserDTO> userDTOs = new ArrayList<>();
            for(User user: users) {
                userDTOs.add(mapper.map(user, UserDTO.class));
            }
            return userDTOs;
        }  catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new HotStockException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

}
