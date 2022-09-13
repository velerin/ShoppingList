package com.example.shoppingList.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="lists")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title",nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.DETACH })
    @JoinColumn(name = "product_id",nullable = false)
    private List<Product> products;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.REFRESH,CascadeType.DETACH },optional = false)
    private User user;

    public ProductList() {
    }

    public ProductList(int id, String title, List<Product> products) {
        this.id = id;
        this.title = title;
        this.products = products;
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
}
