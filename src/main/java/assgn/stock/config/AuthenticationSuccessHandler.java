/**
 * 
 */
package assgn.stock.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

/**
 * @author Jogireddy Kotam
 *
 */

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession()
				.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
		if (defaultSavedRequest != null) {
			String requestUrl = defaultSavedRequest.getRequestURL() + "?" + defaultSavedRequest.getQueryString();
			getRedirectStrategy().sendRedirect(request, response, requestUrl);
		} else {
			// super.onAuthenticationSuccess(request, response, authentication);
			request.getRequestDispatcher("/loginSuccess").forward(request, response);
		}

	}

}
