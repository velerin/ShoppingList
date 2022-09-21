package com.example.shoppingList.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount", nullable = false)
    private int amount = -1;

    @Column(name = "product_name", nullable = false)
    private String productName = "Default product for new user";

    @Column(name = "price_per_piece", nullable = false)
    private double pricePerPiece = -1.00;

    @Column(name = "currency", nullable = false)
    private String currency = "PLN";

    @ManyToOne(cascade = {CascadeType.MERGE,
                          CascadeType.DETACH,
                          CascadeType.PERSIST,
                          CascadeType.PERSIST,
                          CascadeType.REFRESH})
    @JoinColumn(name = "product_list_id")
    private ProductList productList;

    public Product() {
    }

    public Product(int amount, String productName, double pricePerPiece, String currency) {
        this.amount = amount;
        this.productName = productName;
        this.pricePerPiece = pricePerPiece;
        this.currency = currency;
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

    public double getPricePerPiece() {
        return pricePerPiece;
    }

    public void setPricePerPiece(double pricePerPiece) {
        this.pricePerPiece = pricePerPiece;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", amount=" + amount +
                ", productName='" + productName + '\'' +
                ", pricePerPiece=" + pricePerPiece +
                ", currency='" + currency + '\'' +
                ", productList=" + productList +
                '}';
    }
}
