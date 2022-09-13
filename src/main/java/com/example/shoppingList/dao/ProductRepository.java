package com.example.shoppingList.dao;

import com.example.shoppingList.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Integer> {
}
