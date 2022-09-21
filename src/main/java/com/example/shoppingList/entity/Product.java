package com.example.shoppingList.entity;

import com.example.shoppingList.validation.CurrencyValid;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Min(value=0, message = "is negative")
    @Column(name = "amount", nullable = false)
    private int amount = -1;

    @NotNull
    @Size(min=1, message = "is required")
    @Column(name = "product_name", nullable = false)
    private String productName = "Default product for new user";

    @NotNull
    @Min(value=0, message = "is negative")
    @Column(name = "price_per_piece", nullable = false)
    private double pricePerPiece = -1.00;

    @NotNull
    @CurrencyValid
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
                ", currency='" + currency + "'";
    }

}
