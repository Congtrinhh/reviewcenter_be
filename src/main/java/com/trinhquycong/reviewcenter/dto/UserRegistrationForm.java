package com.trinhquycong.reviewcenter.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.trinhquycong.reviewcenter.validator.PasswordMatches;

@PasswordMatches
public class UserRegistrationForm {
	
	private Long userID;
	
	private String providerUserId;
	
	@NotBlank
	private String displayName;
	
	@NotBlank
	private String email;
	
	private SocialProvider socialProvider;
	
	@Size(min = 8, message = "{Size.userDto.password}")
	private String password;
	
	@NotBlank
	private String matchingPassword;
	
	public UserRegistrationForm() {
		
	}
	
	public UserRegistrationForm(String providerUserId, String displayName, String email, String password, SocialProvider socialProvider) {
        this.providerUserId = providerUserId;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.socialProvider = socialProvider;
    }

	public static Builder getBuilder() {
        return new Builder();
    }
	
	public static class Builder {
        private String providerUserID;
        private String displayName;
        private String email;
        private String password;
        private SocialProvider socialProvider;
 
        public Builder addProviderUserID(final String userID) {
            this.providerUserID = userID;
            return this;
        }
 
        public Builder addDisplayName(final String displayName) {
            this.displayName = displayName;
            return this;
        }
 
        public Builder addEmail(final String email) {
            this.email = email;
            return this;
        }
 
        public Builder addPassword(final String password) {
            this.password = password;
            return this;
        }
 
        public Builder addSocialProvider(final SocialProvider socialProvider) {
            this.socialProvider = socialProvider;
            return this;
        }
 
        public UserRegistrationForm build() {
            return new UserRegistrationForm(providerUserID, displayName, email, password, socialProvider);
        }
    }

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SocialProvider getSocialProvider() {
		return socialProvider;
	}

	public void setSocialProvider(SocialProvider socialProvider) {
		this.socialProvider = socialProvider;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
}
