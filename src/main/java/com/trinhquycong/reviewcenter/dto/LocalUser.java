package com.trinhquycong.reviewcenter.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.trinhquycong.reviewcenter.entity.User;
import com.trinhquycong.reviewcenter.util.GeneralUtils;

public class LocalUser extends org.springframework.security.core.userdetails.User implements OAuth2User, OidcUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final OidcIdToken idToken; // authenticated id
	
	private final OidcUserInfo userInfo; // authenticated user info
	
	private Map<String, Object> attributes; // for what??
	
	private User user; // user entity
	
	
	public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
            final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final User user) {
        this(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, user, null, null);
    }
 
    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
            final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final User user, OidcIdToken idToken,
            OidcUserInfo userInfo) {
        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
        this.idToken = idToken;
        this.userInfo = userInfo;
    }
    
    public static LocalUser create(User user, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        LocalUser localUser = new LocalUser(user.getEmail(), user.getPassword(), user.getActive(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()),
                user, idToken, userInfo);
        localUser.setAttributes(attributes);
        return localUser;
    }

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.user.getDisplayName();
	}

	@Override
	public Map<String, Object> getClaims() {
		// TODO Auto-generated method stub
		return this.attributes;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		// TODO Auto-generated method stub
		return this.userInfo;
	}

	@Override
	public OidcIdToken getIdToken() {
		// TODO Auto-generated method stub
		return this.idToken;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public void setUser(User user) {
		this.user = user;
	}
  
}
