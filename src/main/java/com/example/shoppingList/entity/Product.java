package com.example.shoppingList.entity;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "amount",nullable = false)
    private int amount = 1;

    @Column(name = "product_name",nullable = false)
    private String productName = "Default product for new user";

    public Product() {
    }

    public Product( String productName,int amount) {
        this.amount = amount;
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", amount=" + amount +
                ", productName='" + productName + '\'' +
                '}';
    }
}
