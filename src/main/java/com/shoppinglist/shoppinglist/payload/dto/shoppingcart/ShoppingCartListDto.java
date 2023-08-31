package com.shoppinglist.shoppinglist.payload.dto.shoppingcart;

import java.util.Date;
import java.util.UUID;

public class ShoppingCartListDto {
    private Long id;
    private UUID uuid;
    private String name;
    private int productsAmount;
    private Date createdAt;

    public ShoppingCartListDto() {
    }

    public ShoppingCartListDto(
            Long id,
            UUID uuid,
            String name,
            int productsAmount,
            Date createdAt) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.productsAmount = productsAmount;
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

    public int getProductsAmount() {
        return productsAmount;
    }

    public void setProductsAmount(int productsAmount) {
        this.productsAmount = productsAmount;
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
