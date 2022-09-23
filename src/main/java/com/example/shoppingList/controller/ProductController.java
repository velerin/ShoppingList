package com.example.shoppingList.controller;

import com.example.shoppingList.dao.ProductListRepository;
import com.example.shoppingList.dao.ProductRepository;
import com.example.shoppingList.entity.Product;
import com.example.shoppingList.entity.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductListRepository productListRepository;

    @GetMapping("/{userId}/{listId}/showFormForAdd")
    public String showFormForAdd(@PathVariable Integer userId,
                                 @PathVariable Integer listId,
                                 @RequestParam(required = false) Integer productId,
                                 Model model) {
        Product product;
        if (productId != null) {
            product = productRepository.getReferenceById(productId);

        } else {
            product = new Product();
        }

        model.addAttribute("product", product);
        model.addAttribute("userId", userId);
        model.addAttribute("listId", listId);

        return "products/product-form";
    }

    @PostMapping("/{userId}/{listId}/save")
    public String save(@PathVariable Integer userId,
                       @PathVariable Integer listId,
                       @Valid @ModelAttribute("product") Product product,
                       BindingResult bindingResult){

        System.out.println("bindingResult: "+bindingResult);

        if (bindingResult.hasErrors()){
            return "products/product-form";
        }

        if (product.getId() == 0) {
            ProductList productList = productListRepository.getReferenceById(listId);
            product.setProductList(productList);
            productRepository.save(product);
        } else {
            Product productFromRepository = productRepository.getReferenceById(product.getId());
            productFromRepository.setAmount(product.getAmount());
            productFromRepository.setProductName(product.getProductName());
            productFromRepository.setCurrency(product.getCurrency());
            productFromRepository.setPricePerPiece(product.getPricePerPiece());
            productRepository.save(productFromRepository);
        }


        return "redirect:/shoppinglists/" + userId + "/showProductLists/";
    }

    @GetMapping("/{userId}/delete")
    public String delete(@PathVariable Integer userId,
                         @RequestParam Integer productId){

        productRepository.deleteById(productId);

        return "redirect:/shoppinglists/" + userId + "/showProductLists/";

    }

}
