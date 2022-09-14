package com.example.shoppingList.controller;

import com.example.shoppingList.entity.User;
import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/showUsers")
    public String showUsers(Model model){
        model.addAttribute("users",userService.findAll(Sort.by(Sort.Direction.ASC,"lastName")));
        return "users/list-users";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate( @RequestParam("userId") final int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
            userService.save(user);
        return "redirect:/users/showUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") final int id){
        userService.delete(id);
        return "redirect:/users/showUsers";
    }

}
