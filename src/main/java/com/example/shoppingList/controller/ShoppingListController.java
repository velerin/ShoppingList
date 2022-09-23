package com.example.shoppingList.controller;

import com.example.shoppingList.constants.ProductListFieldsForView;
import com.example.shoppingList.constants.ProductsFieldsForView;
import com.example.shoppingList.dao.ProductListRepository;
import com.example.shoppingList.dao.ProductRepository;
import com.example.shoppingList.entity.Product;
import com.example.shoppingList.entity.ProductList;
import com.example.shoppingList.entity.User;
import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shoppinglists")
public class ShoppingListController {

    @Autowired
    private ProductListRepository productListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("user2ServiceImpl") 
    private UserService userService;

    @GetMapping(value = {"/{userId}/showProductLists", "/{userId}/showProductLists/{listId}"})
    public String showProductsListForUser(@PathVariable final Integer userId, @PathVariable(required = false) final Integer listId, @RequestParam(name = "listName", required = false) String listName, @RequestParam(name = "sort", required = false) Integer sort, Model model) {


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
                    return  Double.compare(s1.getAmount(), s2.getAmount());
                case 3:
                    return Double.compare(s1.getPricePerPiece(), s2.getPricePerPiece());
                case 4:
                    return s1.getCurrency().compareTo(s2.getCurrency());
                case 5:
                    return Double.compare(s1.getAmount() * s1.getPricePerPiece(), s2.getAmount() * s2.getPricePerPiece());
                default:
                    return s1.getProductName().compareTo(s2.getProductName());
            }
        }));

        model.addAttribute("userId", userId);
        model.addAttribute("sumsMap", convertFromProductListToMap(list));
        model.addAttribute("productLists", list);
        model.addAttribute("productListNames", ProductListFieldsForView.all());
        model.addAttribute("productNames", ProductsFieldsForView.all());

        return "products/products-list";
    }


    @GetMapping("/{userId}/showFormForAdd")
    public String showFormForAdd(@PathVariable final Integer userId, @RequestParam(required = false) final Integer listId, Model model) throws Exception {

        ProductList productList;
        if (listId != null) {
            productList = productListRepository.getReferenceById(listId);
        } else {
            productList = new ProductList();
        }

        model.addAttribute("list", productList);
        model.addAttribute("userId", userId);

        return "products/product-list-form";
    }

    @PostMapping("/{userId}/save")
    public String save(@PathVariable final Integer userId,
                       @Valid @ModelAttribute("list") ProductList list,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "products/product-list-form";
        }

        if (list.getId() == 0) {
            User user = userService.findById(userId);
            list.setUser(user);
            productListRepository.save(list);
        } else {
            ProductList productList = productListRepository.getReferenceById(list.getId());
            productList.setTitle(list.getTitle());
            productListRepository.save(productList);
        }

        return "redirect:/shoppinglists/" + userId + "/showProductLists/";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable("userId") final Integer userId, @RequestParam("listId") final Integer listId) {
        productListRepository.deleteById(listId);
        return "redirect:/shoppinglists/" + userId + "/showProductLists/";
    }

    private Map<Integer, Double> convertFromProductListToMap(List<ProductList> productLists) {
        Map<Integer, Double> sumsMap = new HashMap<>();
        for (ProductList productList : productLists) {
            sumsMap.put(productList.getId(), 0.0);
        }

        for (ProductList productList : productLists) {
            for (Product product : productList.getProducts()) {
                if (product.getProductList().getId() == productList.getId()) {
                    int key = productList.getId();
                    double value = sumsMap.get(key);
                    value += product.getPricePerPiece() * product.getAmount();
                    sumsMap.put(key, value);
                }
            }
        }
        return sumsMap;
    }
}
