package com.shoppinglist.shoppinglist.payload.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;

public class ProductUpdateDto {
    private String name;
    private UrgencyLevel urgencyLevel;

    public ProductUpdateDto() {
    }

    public ProductUpdateDto(
            String name,
            UrgencyLevel urgencyLevel) {
        this.name = name;
        this.urgencyLevel = urgencyLevel;
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
}
