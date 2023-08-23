package com.shoppinglist.shoppinglist.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private List<Product> products = new ArrayList<>();

    @CreationTimestamp
    private Date createdAt;

    public ShoppingCart() {
    }

    public ShoppingCart(Long id,
                        String name, List<Product> products, Date createdAt) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
