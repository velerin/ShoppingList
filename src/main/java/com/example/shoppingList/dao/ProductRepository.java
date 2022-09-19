package com.example.shoppingList.dao;

import com.example.shoppingList.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllProductsByProductListId(int product_list_id);
}
