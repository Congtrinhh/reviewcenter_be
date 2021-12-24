package com.trinhquycong.reviewcenter.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.trinhquycong.reviewcenter.dto.AdminRegisterDto;
import com.trinhquycong.reviewcenter.entity.Role;

public class RoleMatchesValidator implements ConstraintValidator<RoleMatches, AdminRegisterDto> {

	@Override
	public boolean isValid(AdminRegisterDto user, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		for (String roleName : user.getRoles()) {
			if (roleName.equalsIgnoreCase(Role.ROLE_USER)) {
				return false;
			}
		}
		return true;
	}

}
