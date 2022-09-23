package com.example.shoppingList.dao;

import com.example.shoppingList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrUserNameIgnoreCase(String email,String userName);

    List<User> findAllByFirstNameContainingIgnoreCase(String firstName);

}
