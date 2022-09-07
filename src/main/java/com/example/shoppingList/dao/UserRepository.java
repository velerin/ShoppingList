package com.example.shoppingList.dao;

import com.example.shoppingList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUserName(String userName);
}
