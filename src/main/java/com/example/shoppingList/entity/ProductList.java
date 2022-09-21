package com.example.shoppingList.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "lists")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "is required")
    @Size(min=1,message = "is required")
    @Column(name = "title", nullable = false)
    private String title = "Default list for new user";

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_list_id")
    private List<Product> products;

    @ManyToOne(cascade = {CascadeType.MERGE,
                          CascadeType.DETACH,
                          CascadeType.PERSIST,
                          CascadeType.PERSIST,
                          CascadeType.REFRESH})
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

    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new LinkedList<>();
        }

        this.products.add(product);
        product.setProductList(this);
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "title='" + title + '\'' +
                ", products=" + products +
                '}';
    }
}
