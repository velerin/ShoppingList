package com.example.shoppingList.dao;

import com.example.shoppingList.entity.Authority;
import com.example.shoppingList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    List<Authority> findAllAuthoritiesByUser(User user);
}