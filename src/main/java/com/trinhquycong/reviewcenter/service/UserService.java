package com.trinhquycong.reviewcenter.service;

import java.util.Map;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.trinhquycong.reviewcenter.dto.LocalUser;
import com.trinhquycong.reviewcenter.dto.UserRegistrationForm;
import com.trinhquycong.reviewcenter.entity.User;
import com.trinhquycong.reviewcenter.exception.UserAlreadyExistAuthenticationException;

public interface UserService {
	
	public User registerNewUser(UserRegistrationForm UserRegistrationForm) throws UserAlreadyExistAuthenticationException;
	
	User findUserByEmail(String email);
	
	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
	
	User save(Map<String, Object> updates, Long id);
	
	String deleteById(Long id);
}
