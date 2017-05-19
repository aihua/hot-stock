package assgn.stock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import assgn.stock.config.auth.AuthProvider;

/**
 * @author Jogireddy Kotam
 *
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:application.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthProvider authProvider;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
    private SignoutSuccessHandler signoutSuccessHandler;
	
	@Autowired
	private LoginFailureHandler loginFailureHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
			.and()
			.formLogin()
			.loginPage("/").loginProcessingUrl("/login")
			.successHandler(authenticationSuccessHandler)
			.failureHandler(loginFailureHandler)
			.failureUrl("/loginFailure")
			.permitAll()
			.usernameParameter("username");
		http
			.authorizeRequests()
			.antMatchers("/login","/","/loginSuccess","/logout-success","/loginFailure",
					"/assets/**", "/lib/**", "/admin/**","/broker/**").permitAll()
			.antMatchers("/index.html", "/home.html", "/assets/**").permitAll().anyRequest()
			.authenticated();
		http
		    .logout()
		    .deleteCookies("JSESSIONID")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.invalidateHttpSession(true).clearAuthentication(true).logoutSuccessHandler(signoutSuccessHandler);
	}
	

}
