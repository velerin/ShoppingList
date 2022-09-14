package com.example.shoppingList.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "authority",nullable = false)
    private String authority;

    @Column(name = "username",nullable = false)
    private String userName;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Authority() {
    }

    public Authority(String userName, String authority) {
        this.userName = userName;
        this.authority = authority;
    }

    public Authority(String authority, String userName, User user) {
        this.authority = authority;
        this.userName = userName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority1 = (Authority) o;
        return authority.equals(authority1.authority) && userName.equals(authority1.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority, userName);
    }
}
