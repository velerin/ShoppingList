package com.example.shoppingList.dao;

import com.example.shoppingList.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {

}