package com.trinhquycong.reviewcenter.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.client.RestTemplate;

import com.trinhquycong.reviewcenter.entity.Role;
import com.trinhquycong.reviewcenter.oauth2.CustomOAuth2UserService;
import com.trinhquycong.reviewcenter.oauth2.CustomOidcUserService;
import com.trinhquycong.reviewcenter.oauth2.OAuth2AccessTokenResponseConverterWithDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] IGNORED_RESOURCE_LIST = new String[] { "/fonts/**", "/webjars/**", "/files/**",
			"/static/**", "/robots.txt" };

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	CustomOidcUserService customOidcUserService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		//http.authorizeRequests()
			//.antMatchers("/admin/users/**").hasAuthority(Role.ROLE_ADMIN)
			//.antMatchers("/admin/**").hasAnyAuthority(Role.ROLE_ADMIN, Role.ROLE_MANAGER);
//			.and().rememberMe().key("rememberMeToken").tokenValiditySeconds(3600*24*7);
		
		//http.authorizeRequests().antMatchers("/", "/home").hasAuthority(Role.ROLE_USER);
		// Role.ROLE_USER
		// Pages do not require login
		http.authorizeRequests().antMatchers("/**").permitAll();

		// Form Login config
		http.authorizeRequests().and().formLogin()//
				// Submit URL of login page.
				// .loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/home")//
				.failureUrl("/login?error=true").failureHandler(authenticationFailureHandler)//
				.usernameParameter("j_username")//
				.passwordParameter("j_password").and().oauth2Login().loginPage("/login").failureHandler(authenticationFailureHandler).defaultSuccessUrl("/home").userInfoEndpoint()
				.oidcUserService(customOidcUserService).userService(customOAuth2UserService).and().tokenEndpoint()
				.accessTokenResponseClient(authorizationCodeTokenResponseClient());
		// Logout Config
		http.authorizeRequests().and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
		//
		http.sessionManagement().invalidSessionUrl("/login").maximumSessions(1).expiredUrl("/login?session=expired");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	// This bean is load the user specific data when form login is used.
	@Override
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(IGNORED_RESOURCE_LIST);
	}
	
	@SuppressWarnings("deprecation")
	private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
		OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
		tokenResponseHttpMessageConverter.setTokenResponseConverter(new OAuth2AccessTokenResponseConverterWithDefaults());
		RestTemplate restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
		DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
		tokenResponseClient.setRestOperations(restTemplate);
		return tokenResponseClient;
	}
}
