package com.example.shoppingList.service;

import com.example.shoppingList.constants.Authorities;
import com.example.shoppingList.dao.AuthorityRepository;
import com.example.shoppingList.dao.UserRepository;
import com.example.shoppingList.entity.Authority;
import com.example.shoppingList.entity.Product;
import com.example.shoppingList.entity.ProductList;
import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserModel userModel) {
        if (checkIfUserExist(userModel.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        Authority authority = new Authority(userModel.getUserName(), Authorities.USER.value);
        authority.setId(0);
        User user = new User(0, userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getUserName(),
                passwordEncoder.encode(userModel.getPassword()), true, Collections.singletonList(authority));

        ProductList list = new ProductList();

        user.setProductLists(Collections.singletonList(list));

        list.addProduct(new Product());

        userRepository.save(user);

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getUserName())).collect(Collectors.toList());
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public void save(User user) {
        User user1 = findById(user.getId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        Collection<Authority> authorities = user.getAuthorities();
        if (authorities != null) {
            for (Authority authority : authorities) {
                if (!user1.getAuthorities().contains(authority)) {
                    user1.addAuthority(authority);
                }
            }
        }
        userRepository.save(user1);
    }

    @Override
    public void delete(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            System.out.println(user.get());
            userRepository.delete(user.get());
        }
    }

    @Override
    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseGet(User::new);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findAllByFirstNameContainingIgnoreCase(firstName);
    }


}
