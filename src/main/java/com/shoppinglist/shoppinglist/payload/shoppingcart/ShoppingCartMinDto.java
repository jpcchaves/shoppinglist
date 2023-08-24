package com.shoppinglist.shoppinglist.payload.shoppingcart;

import java.util.UUID;

public class ShoppingCartMinDto {
    private Long id;
    private UUID uuid;
    private String name;

    public ShoppingCartMinDto() {
    }

    public ShoppingCartMinDto(
            Long id,
            UUID uuid,
            String name) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
