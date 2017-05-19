/**
 * 
 */
package assgn.stock.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assgn.stock.utils.Role;

/**
 * @author Jogireddy Kotam
 * @date 17-May-2017
 *
 */

@RestController
@RequestMapping("/")
public class SecurityController {
    
    private static final Logger logger = LogManager.getLogger(SecurityController.class);
    
    private static String adminState = "admin.home";
    
    private static String brokerState = "broker.home";
    
    private static String home = "login";
    
    @RequestMapping(value = "/loginSuccess", produces = "application/json")
    public ResponseEntity<?> loginSuccess(HttpServletRequest request, HttpServletResponse response){
        logger.info("Authentication Success at " + new Date().toString());
        Map<String, String> content = new HashMap<>();
        content.put("response", "Login was Successful");
        @SuppressWarnings("unchecked")
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if(checkRoleExists(Role.ROLE_ADMIN.toString(), authorities)){
            content.put("state", adminState);
        } else if(checkRoleExists(Role.ROLE_BROKER.toString(), authorities) ) {
            content.put("state", brokerState);
        } else {
            content.put("state", home);
        }
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
    
    private boolean checkRoleExists(String role, Collection<SimpleGrantedAuthority> authorities) {
        boolean flag = false;
        for(SimpleGrantedAuthority authority: authorities) {
            if(role.equals(authority.getAuthority())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
    
    @RequestMapping(value = "/logout-success", produces = "application/json")
    public ResponseEntity<?> logoutSuccess(HttpServletRequest request, HttpServletResponse response){
        logger.info("Authentication logoutSuccess");
        Map<String, String> content = new HashMap<>();
        content.put("response", "Logout was Successful");
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/loginFailure", produces = "application/json")
    public ResponseEntity<?> loginFailure(HttpServletRequest request, HttpServletResponse response){
        logger.info("Authentication Failure at " + new Date().toString());
        return new ResponseEntity<>(Collections.singletonMap("response", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")), HttpStatus.UNAUTHORIZED);
    }
    
    private String getErrorMessage(HttpServletRequest request, String key) {
        logger.info("Error Message requested at " + new Date().toString());
        Exception exception = (Exception) request.getSession().getAttribute(key);
        if (exception instanceof BadCredentialsException || exception instanceof LockedException || exception instanceof AuthenticationException) {
            return exception.getMessage();
        } else {
            return "Invalid username or Password! ";
        }
    }
    
    @RequestMapping(value = "/invalid-session", produces = "application/json")
    public ResponseEntity<Object> invaildSession() {
        return new ResponseEntity<>(Collections.singletonMap("response", "Your session has been expired. Please login again."), HttpStatus.GATEWAY_TIMEOUT);
    }
}
