package com.example.shoppingList.controller;

import com.example.shoppingList.constants.UserFieldsInView;
import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/showUsers")
    public String showUsers(
            Model model,
            @RequestParam(name = "sort", required = false) Integer sort,
            @RequestParam(name = "name", required = false) String name) {

        List<User> users;
        UserFieldsInView sortConst = UserFieldsInView.LAST_NAME;
        if (sort != null) {
            switch (sort) {
                case 1:
                    sortConst = UserFieldsInView.FIRST_NAME;
                    break;
                case 3:
                    sortConst = UserFieldsInView.EMAIL;
                    break;
                default:

            }
        }
        if (name == null) {
            users = userService.findAll(Sort.by(Sort.Direction.ASC, sortConst.label));
        } else {
            users = userService.findByFirstName(name);
            model.addAttribute("name",name);
            System.out.println(name);
        }


        model.addAttribute("userFields", UserFieldsInView.all());
        model.addAttribute("users", users);

        return "users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("from", "user");
        return "registration-form.html";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") final int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/showUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") final int id) {
        userService.delete(id);
        return "redirect:/users/showUsers";
    }

}
