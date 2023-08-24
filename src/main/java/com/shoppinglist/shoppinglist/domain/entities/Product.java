package com.shoppinglist.shoppinglist.domain.entities;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private UrgencyLevel urgencyLevel;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    @CreationTimestamp
    private Date createdAt;

    public Product() {
    }

    public Product(
            String name,
            UrgencyLevel urgencyLevel,
            ShoppingCart shoppingCart) {
        this.name = name;
        this.urgencyLevel = urgencyLevel;
        this.shoppingCart = shoppingCart;
    }

    public Product(
            Long id,
            String name,
            UrgencyLevel urgencyLevel,
            ShoppingCart shoppingCart,
            Date createdAt) {
        this.id = id;
        this.name = name;
        this.urgencyLevel = urgencyLevel;
        this.shoppingCart = shoppingCart;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UrgencyLevel getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(UrgencyLevel urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
