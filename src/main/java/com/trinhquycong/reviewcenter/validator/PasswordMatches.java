package com.trinhquycong.reviewcenter.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE}) // ERRORABLE
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {
	String message() default "Passwords don't match";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}
