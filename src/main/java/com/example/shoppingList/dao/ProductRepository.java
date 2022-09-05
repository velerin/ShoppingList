package com.example.shoppingList.dao;

import com.example.shoppingList.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "shoppinglist")
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
