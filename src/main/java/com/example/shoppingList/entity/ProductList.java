package com.example.shoppingList.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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


    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new LinkedList<>();
        }

        this.products.add(product);
        product.setProductList(this);
    }

}
