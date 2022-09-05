package com.example.shoppingList.dao;

import com.example.shoppingList.entity.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductListRepository extends JpaRepository<ProductList,Integer> {
}
