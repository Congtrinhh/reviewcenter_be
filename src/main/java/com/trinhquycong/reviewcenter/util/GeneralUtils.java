package com.trinhquycong.reviewcenter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.trinhquycong.reviewcenter.dto.SocialProvider;
import com.trinhquycong.reviewcenter.entity.Role;

public class GeneralUtils {

	/**
	 * for sso login
	 * @param roles
	 * @return
	 */
	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(Set<Role> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		roles.forEach(role-> authorities.add(new SimpleGrantedAuthority(role.getName())));
		
		return authorities;
	}
	
	/**
	 * for sso login
	 * @param providerId
	 * @return
	 */
	public static SocialProvider toSocialProvider(String providerId) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.getProviderType().equals(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.LOCAL;
    }
}
