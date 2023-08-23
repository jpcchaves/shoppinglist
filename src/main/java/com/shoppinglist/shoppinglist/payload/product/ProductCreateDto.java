package com.shoppinglist.shoppinglist.payload.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;

public class ProductCreateDto {
    private String name;
    private UrgencyLevel urgencyLevel;
    private Long shoppingCartId;

    public ProductCreateDto() {
    }

    public ProductCreateDto(
            String name,
            UrgencyLevel urgencyLevel,
            Long shoppingCartId) {
        this.name = name;
        this.urgencyLevel = urgencyLevel;
        this.shoppingCartId = shoppingCartId;
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

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
