package com.example.shoppingList.service;

import com.example.shoppingList.dao.RoleRepository;
import com.example.shoppingList.dao.UserRepository;
import com.example.shoppingList.entity.Role;
import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserModel userModel) {
        if(checkIfUserExist(userModel.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        Role role = roleRepository.findByName("ROLE_EMPLOYEE");
        role.setId(0);
        User user = new User(0, userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(),
                passwordEncoder.encode( userModel.getPassword()),Collections.singletonList(role));
        userRepository.save(user);

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
