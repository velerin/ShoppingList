package com.example.shoppingList.service;

import com.example.shoppingList.model.UserModel;

public interface UserService {

    void register(UserModel userModel);

    boolean checkIfUserExist(String email);

}
