package com.trinhquycong.reviewcenter.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = -7441622614372360580L;

	public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
}
