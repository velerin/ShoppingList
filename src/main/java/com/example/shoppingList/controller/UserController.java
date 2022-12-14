package com.example.shoppingList.controller;

import com.example.shoppingList.constants.Authorities;
import com.example.shoppingList.constants.UserFieldsForView;
import com.example.shoppingList.entity.Authority;
import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private UserService userService;

    @GetMapping("/showUsers")
    public String showUsers(
            Model model,
            @RequestParam(name = "sort", required = false) Integer sort,
            @RequestParam(name = "name", required = false) String name) {

        List<User> users;
        UserFieldsForView sortConst = UserFieldsForView.LAST_NAME;
        if (sort != null) {
            switch (sort) {
                case 1:
                    sortConst = UserFieldsForView.FIRST_NAME;
                    break;
                case 3:
                    sortConst = UserFieldsForView.EMAIL;
                    break;
                default:

            }
        }

        if (name == null) {
            users = userService.findAll(Sort.by(Sort.Direction.ASC, sortConst.label));
        } else {
            users = userService.findByFirstName(name);
            model.addAttribute("name", name);
        }
        model.addAttribute("userFields", UserFieldsForView.all());
        model.addAttribute("users", users);
        return "users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("from", "user");
        return "registration-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") final Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("userId") Integer id,
                       @ModelAttribute("user") User user,
                       HttpSession session) {
        User userFromRepo = userService.findById(user.getId());

        userFromRepo.setFirstName(user.getFirstName());
        userFromRepo.setLastName(user.getLastName());
        userFromRepo.setEmail(user.getEmail());
        userFromRepo.setEnabled(user.isEnabled());

        logger.info(userFromRepo.toString());
        userService.save(userFromRepo);

        if (!userFromRepo.getAuthorities().contains(new Authority(userFromRepo.getUsername(), Authorities.MANAGER.value)) ||
                !userFromRepo.getAuthorities().contains(new Authority(userFromRepo.getUsername(), Authorities.ADMIN.value))) {
            session.setAttribute("user", userFromRepo);
            return "redirect:/";
        }
        return "redirect:/users/showUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") final int id) {
        userService.delete(id);
        return "redirect:/users/showUsers";
    }

}
