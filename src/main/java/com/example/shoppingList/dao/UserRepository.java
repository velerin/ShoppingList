package com.example.shoppingList.dao;

import com.example.shoppingList.entity.UserForList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserForList,Integer> {
}
