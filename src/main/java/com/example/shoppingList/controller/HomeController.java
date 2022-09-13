package com.example.shoppingList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "user";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/admins")
    public String systems(){
        return "admins";
    }

    @GetMapping("/leaders")
    public String leaders(){
        return "leaders";
    }


}
