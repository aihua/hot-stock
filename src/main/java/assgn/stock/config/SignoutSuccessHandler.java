/**
 * 
 */
package assgn.stock.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author Jogireddy Kotam
 *
 */
@Component("signoutSuccessHandler")
public class SignoutSuccessHandler implements LogoutSuccessHandler {
    
    private static final Logger logger = LogManager.getLogger(SignoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication != null) {
            logger.info("Successfully logged-out : " + authentication.getName());
        }
        logger.info("Successfully logged-out : ");
        request.getRequestDispatcher("/logout-success").forward(request, response);
    }

}
