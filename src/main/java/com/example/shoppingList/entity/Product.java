package com.example.shoppingList.entity;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "product_name")
    private String productName;

    public Product() {
    }

    public Product(int id, int amount, String productName) {
        this.id = id;
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
