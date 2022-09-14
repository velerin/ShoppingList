package com.example.shoppingList.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = EmailUniquenessValidation.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueEmail {
	String message() default "is already in use";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
