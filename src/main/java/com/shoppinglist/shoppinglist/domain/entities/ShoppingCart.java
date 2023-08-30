package com.shoppinglist.shoppinglist.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

    @CreationTimestamp
    private Date createdAt;

    public ShoppingCart() {
    }

    public ShoppingCart(
            Long id,
            String name,
            UUID uuid) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
    }

    public ShoppingCart(
            Long id,
            String name,
            String description,
            UUID uuid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.uuid = uuid;
    }

    public ShoppingCart(
            Long id,
            String name,
            UUID uuid,
            List<Product> products,
            Date createdAt) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
