package com.shoppinglist.shoppinglist.payload.dto.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;

import java.math.BigDecimal;

public class ProductUpdateDto {
    private String name;
    private UrgencyLevel urgencyLevel;
    private BigDecimal productPrice;
    private Integer productQuantity;

    public ProductUpdateDto() {
    }

    public ProductUpdateDto(
            String name,
            UrgencyLevel urgencyLevel) {
        this.name = name;
        this.urgencyLevel = urgencyLevel;
    }

    public ProductUpdateDto(
            String name,
            UrgencyLevel urgencyLevel,
            BigDecimal productPrice,
            Integer productQuantity) {
        this.name = name;
        this.urgencyLevel = urgencyLevel;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
