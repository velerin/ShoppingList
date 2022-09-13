package com.example.shoppingList.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "authority",nullable = false)
    private String authority;

    @Column(name = "username",nullable = false)
    private String userName;
    
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.DETACH },optional = false)
    private User user;

    public Authorities() {
    }

    public Authorities(String userName, String authority) {
        this.userName = userName;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
