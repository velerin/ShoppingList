package com.example.shoppingList.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "authority",nullable = false)
    private String authority;

    @Column(name = "username",nullable = false)
    private String userName;

    @ManyToOne(cascade = {CascadeType.MERGE,
                          CascadeType.DETACH,
                          CascadeType.PERSIST,
                          CascadeType.PERSIST,
                          CascadeType.REFRESH})
    private User user;


    public Authority(String userName, String authority) {
        this.userName = userName;
        this.authority = authority;
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

    @Override
    public String toString() {
        String cutString = authority.substring(authority.indexOf("_")+1);
        return cutString.charAt(0)+cutString.substring(1).toLowerCase();
    }
}
