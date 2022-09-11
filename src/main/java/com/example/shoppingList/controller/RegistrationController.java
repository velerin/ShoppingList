package com.example.shoppingList.controller;

import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("userModel", new UserModel());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userModel") UserModel userModel,
            BindingResult theBindingResult,
            Model theModel) {

        if (theBindingResult.hasErrors()){
            return "registration-form";
        }

        String userName = userModel.getUserName();

        User existing = userService.findByUserName(userName);
        if (existing != null){
            theModel.addAttribute("userModel", new UserModel());
            theModel.addAttribute("registrationError", "User name already exists.");

            return "registration-form";
        }

        // create user account
        userService.register(userModel);

        return "registration-confirmation";
    }
}
