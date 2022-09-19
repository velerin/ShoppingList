package com.example.shoppingList.dao;

import com.example.shoppingList.entity.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "shoppinglists")
public interface ProductListRepository extends JpaRepository<ProductList,Integer> {

    List<ProductList> findAllByUserId(int id);
    List<ProductList> findAllByUserIdAndTitle(int id,String title);
}
