package com.example.shoppingList.dao;

import com.example.shoppingList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    User findByUserName(String userName);

    User findByEmail(String email);


}
