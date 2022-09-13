package com.example.shoppingList.dao;

import com.example.shoppingList.entity.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "shoppinglists")
public interface ProductListRepository extends JpaRepository<ProductList,Integer> {
}
