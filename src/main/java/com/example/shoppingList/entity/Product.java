package com.example.shoppingList.entity;

import com.example.shoppingList.validation.CurrencyValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "is required")
    @DecimalMin(value = "0.0",inclusive = false,message = "is negative")
    @Digits(integer=10, fraction=3,message = "have more than two fractional digits")
    @Column(name = "amount", nullable = false)
    private Double amount = 1.00;

    @NotNull(message = "is required")
    @Size(min=1, message = "is required")
    @Column(name = "product_name", nullable = false)
    private String productName = "Default product";

    @NotNull(message = "is required")
    @DecimalMin(value = "0.0",inclusive = false,message = "is negative")
    @Digits(integer=10, fraction=2,message = "have more than two fractional digits")
    @Column(name = "price_per_piece", nullable = false)
    private Double pricePerPiece = 1.00;

    @NotNull(message = "is required")
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

    public Product(Double amount, String productName, Double pricePerPiece, String currency) {
        this.amount = amount;
        this.productName = productName;
        this.pricePerPiece = pricePerPiece;
        this.currency = currency;
    }

}
