package com.example.shoppingList.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="lists")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title",nullable = false)
    private String title = "Default list for new user";

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.DETACH })
    @JoinColumn(name = "product_id")
    private List<Product> products;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.DETACH })
    private User user;

    public ProductList() {
    }
    public ProductList(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addProduct(Product product){
        if(this.products==null){
            this.products = new LinkedList<>();
        }

        this.products.add(product);
    }

}
