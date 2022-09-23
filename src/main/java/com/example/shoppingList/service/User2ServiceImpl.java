package com.example.shoppingList.service;

import com.example.shoppingList.constants.Authorities;
import com.example.shoppingList.dao.UserRepository;
import com.example.shoppingList.entity.Authority;
import com.example.shoppingList.entity.Product;
import com.example.shoppingList.entity.ProductList;
import com.example.shoppingList.entity.User;
import com.example.shoppingList.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class User2ServiceImpl implements UserDetailsService,UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        return userRepository.findByEmailOrUserNameIgnoreCase(value,value).orElse(null);
    }

    @Override
    public void register(UserModel userModel) {
        if (checkIfUserExist(userModel.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        Authority authority = new Authority(userModel.getUserName(), Authorities.USER.value);
        User user = new User( userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getUserName(),
                passwordEncoder.encode(userModel.getPassword()), true,false, Collections.singletonList(authority));

        ProductList list = new ProductList();
        user.setProductLists(Collections.singletonList(list));
        list.addProduct(new Product());

        userRepository.save(user);
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User findByUserName(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName).orElse(null);
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
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                if (!user1.getAuthorities().contains(authority)) {
                    user1.addAuthority(new Authority(user.getUsername(),authority.getAuthority()));
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
        return userRepository.getReferenceById(id);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findAllByFirstNameContainingIgnoreCase(firstName);
    }
}
