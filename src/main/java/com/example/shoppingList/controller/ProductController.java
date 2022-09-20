package com.example.shoppingList.controller;

import com.example.shoppingList.constants.ProductListFieldsForView;
import com.example.shoppingList.constants.ProductsFieldsForView;
import com.example.shoppingList.dao.ProductListRepository;
import com.example.shoppingList.dao.ProductRepository;
import com.example.shoppingList.entity.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping(value = {"/{userId}/showProductLists", "/{userId}/showProductLists/{listId}"})
    public String showProductsListForUser(@PathVariable final Integer userId,
                                          @PathVariable(required = false) final Integer listId,
                                          @RequestParam(name = "listName", required = false) String listName,
                                          @RequestParam(name = "sort", required = false) Integer sort,
                                          Model model) {


        List<ProductList> list;

        if (listName != null && listId != null) {
            list = productListRepository.findAllByUserIdAndTitleContainingIgnoreCaseAndId(userId, listName, listId);
        } else if (listName != null) {
            list = productListRepository.findAllByUserIdAndTitleContainingIgnoreCase(userId, listName);
        } else if (listId != null) {
            list = productListRepository.findAllByUserIdAndId(userId, listId);
        } else {
            list = productListRepository.findAllByUserId(userId);
        }

        sort = (sort == null ? 1 : sort);
        Integer finalSort = sort;
        list.forEach(s -> s.getProducts().sort((s1, s2) -> {
            switch (finalSort) {
                case 2:
                    return Integer.compare(s1.getAmount(), s2.getAmount());
                case 3:
                    return Double.compare(s1.getPricePerPiece(), s2.getPricePerPiece());
                case 4:
                    return s1.getCurrency().compareTo(s2.getCurrency());
                case 5:
                    return Double.compare(s1.getAmount()*s1.getPricePerPiece(),s2.getAmount()*s2.getPricePerPiece());
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


    @GetMapping("{userId}/showFormForAdd")
    public String showFormForAdd(@PathVariable final Integer userId,
                                 Model model) {
        model.addAttribute("list", new ProductList());
        return "products/product-list-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam final Integer userId,
                                    Model model) throws Exception {

        Optional<ProductList> list = productListRepository.findById(userId);

        if (list.isEmpty()) {
            throw new Exception("NotFound");
        }

        model.addAttribute("user", list.get());

        return "users/user-form";
    }

    @PostMapping("{userId}/save")
    public String save( @PathVariable final Integer userId,
                        @ModelAttribute("list") ProductList list,
                        BindingResult bindingResult) {

        productListRepository.save(list);

        return "redirect:/shoppinglists/showProductLists/" + userId;
    }

    @PostMapping("{userId}/delete")
    public String deleteUser(@PathVariable("userId") final Integer userId,
                             @RequestParam("listId") final Integer listId) {
        productListRepository.deleteById(listId);
        return "redirect:/shoppinglists/showProductLists/" + userId;
    }
}
