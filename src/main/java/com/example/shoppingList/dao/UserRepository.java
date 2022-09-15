package com.example.shoppingList.dao;

import com.example.shoppingList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);

    User findByEmail(String email);

    List<User> findAllByFirstName(String firstName);

}
