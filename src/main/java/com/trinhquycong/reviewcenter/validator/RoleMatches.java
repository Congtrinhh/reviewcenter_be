package com.trinhquycong.reviewcenter.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { RoleMatchesValidator.class })
public @interface RoleMatches {

	String message() default "Can't create a user with role USER";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}
