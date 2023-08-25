package com.shoppinglist.shoppinglist.payload.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;

public class ProductMinDto {
    private Long id;
    private String name;
    private UrgencyLevel urgencyLevel;

    public ProductMinDto() {
    }

    public ProductMinDto(
            Long id,
            String name,
            UrgencyLevel urgencyLevel) {
        this.id = id;
        this.name = name;
        this.urgencyLevel = urgencyLevel;
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
}
