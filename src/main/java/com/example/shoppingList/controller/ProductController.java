package com.example.shoppingList.controller;

import com.example.shoppingList.constants.ProductListFieldsForView;
import com.example.shoppingList.constants.ProductsFieldsForView;
import com.example.shoppingList.dao.ProductListRepository;
import com.example.shoppingList.dao.ProductRepository;
import com.example.shoppingList.entity.ProductList;
import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shoppinglists")
public class ProductController {

    @Autowired
    private ProductListRepository productListRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value={"/showProductLists/{userId}","/showProductLists/{userId}/{listId}"})
    public String showProductsListForUser(@PathVariable final int userId,
                                          @PathVariable(required = false) final Integer listId,
                                          @RequestParam(name = "listName", required = false) String listName,
                                          @RequestParam(name = "sort", required = false) Integer sort,
                                          Model model) {


        List<ProductList> list;

        if(listName!=null&&listId!=null){
            list= productListRepository.findAllByUserIdAndTitleContainingIgnoreCaseAndId(userId,listName,listId);
        } else if (listName != null) {
            list = productListRepository.findAllByUserIdAndTitleContainingIgnoreCase(userId, listName);
        }else if(listId!=null){
            list=productListRepository.findAllByUserIdAndId(userId,listId);
        }else{
            list = productListRepository.findAllByUserId(userId);
        }

        sort = (sort == null ? 1 : sort);
        Integer finalSort = sort;
        list.forEach(s->s.getProducts().sort((s1, s2)->{
            switch (finalSort) {
                case 2:
                    return Integer.compare(s1.getAmount(), s2.getAmount());
                case 3:
                    return Double.compare(s1.getPricePerPiece(), s2.getPricePerPiece());
                case 4:
                    return s1.getCurrency().compareTo(s2.getCurrency());
                default:
                    return s1.getProductName().compareTo(s2.getProductName());
            }
        }));

        model.addAttribute("userId", userId);
        model.addAttribute("productLists", list);
        model.addAttribute("productListNames", ProductListFieldsForView.all());
        model.addAttribute("productNames", ProductsFieldsForView.all());


        return "products/products-list";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("from", "user");
        return "registration-form.html";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") final int id, Model model) throws Exception {

        Optional<ProductList> list = productListRepository.findById(id);

        if (list.isEmpty()) {
            throw new Exception("NotFound");
        }

        model.addAttribute("user", list.get());

        return "users/user-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user) {

//        productListRepository.save(user);

        return "redirect:/users/showUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") final int id) {
//        productListRepository.delete(id);
        return "redirect:/users/showUsers";
    }
}
