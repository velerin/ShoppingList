package com.example.shoppingList.validation;

import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniquenessValidation implements ConstraintValidator<UniqueEmail,String> {

    @Autowired
    private UserService userService;
    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }
        return !userService.checkIfUserExist(email);
    }
}
