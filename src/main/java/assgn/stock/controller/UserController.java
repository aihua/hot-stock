/**
 * 
 */
package assgn.stock.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import assgn.stock.dto.UserDTO;
import assgn.stock.exception.HotStockException;
import assgn.stock.service.IUserService;
import assgn.stock.utils.Role;
import assgn.stock.utils.String2MapUtil;

/**
 * @author Jogireddy Kotam
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
    
    private static final Logger logger = LogManager.getLogger(UserController.class);
    
    @Autowired
    private IUserService userService;
    
    @RequestMapping(value = "/save-broker", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> saveBroker(@RequestBody UserDTO userDTO) {
        logger.info("Entered saveBroker");
        try {
            return new ResponseEntity<>(userService.saveBroker(userDTO), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/brokers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getBrokers(@RequestParam(name = "username")String username) {
        logger.info("Entered getBrokers with username: " + username);
        try {
            return new ResponseEntity<>(userService.findUsersByRoleAndUsername(Role.ROLE_BROKER, username), HttpStatus.OK);
        } catch(HotStockException hotStockException) {
            logger.error("Catched exception with message: " + hotStockException.getMessage(), hotStockException);
            return new ResponseEntity<>(String2MapUtil.convert("error", hotStockException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
