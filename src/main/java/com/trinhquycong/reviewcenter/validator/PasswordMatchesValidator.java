package com.trinhquycong.reviewcenter.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.trinhquycong.reviewcenter.dto.UserRegistrationForm;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRegistrationForm> {

	@Override
    public boolean isValid(final UserRegistrationForm user, final ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
