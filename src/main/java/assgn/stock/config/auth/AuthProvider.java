/**
 * 
 */

package assgn.stock.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import assgn.stock.dto.UserDTO;
import assgn.stock.service.IUserService;
import assgn.stock.utils.Role;

/**
 * @author Jogireddy Kotam
 *
 */
@Component(value = "authProvider")
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            UserDTO user = userService.findByUsername(authentication.getName());

            UsernamePasswordAuthenticationToken authenticationToken = 
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getUsername(), getAuthorities(user.getRole()));

            return authenticationToken;
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Username!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (role != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            authorities.add(authority);
        }
        return authorities;
    }

}
