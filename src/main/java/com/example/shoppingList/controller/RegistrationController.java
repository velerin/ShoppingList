package com.example.shoppingList.controller;

import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    @Qualifier("user2ServiceImpl") 
    private UserService userService;

    private final Logger logger = Logger.getLogger(getClass().getName());
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model) {
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("from","register");
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userModel") UserModel userModel,
            BindingResult bindingResult,
            @ModelAttribute ("from") String from,
            Model model) {

        if (bindingResult.hasErrors()){
            return "registration-form";
        }

        String userName = userModel.getUserName();

        User existing = userService.findByUserName(userName);
        logger.info(userModel.toString());
        if (existing != null){
            model.addAttribute("userModel", new UserModel());
            model.addAttribute("registrationError", "User name already exists.");

            return "registration-form";
        }



        userService.register(userModel);
        if(Objects.equals(from, "user")){
            return "redirect:/users/showUsers";
        }
        return "registration-confirmation";
    }
}
