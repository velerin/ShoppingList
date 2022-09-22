package com.example.shoppingList.validation.service;

import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

    void register(UserModel userModel);

    boolean checkIfUserExist(String email);

    User findByUserName(String userName);

    List<User> findAll(Sort sort);

    void save(User user);

    void delete(int id);

    User findById(int id);

    List<User> findByFirstName(String firstName);
}
