package com.example.shoppingList.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = CurrencyValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrencyValid {

    String message() default "is not valid currency short name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
