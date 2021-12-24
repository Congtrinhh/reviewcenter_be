package com.trinhquycong.reviewcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trinhquycong.reviewcenter.dto.LocalUser;
import com.trinhquycong.reviewcenter.entity.User;
import com.trinhquycong.reviewcenter.util.GeneralUtils;

@Service
public class LocalUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public LocalUser loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findUserByEmail(email);
		System.out.println("in loadUserByUsername()");
		if (user==null) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		
		return new LocalUser(user.getEmail(), user.getPassword(), user.getActive(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
	}

}
