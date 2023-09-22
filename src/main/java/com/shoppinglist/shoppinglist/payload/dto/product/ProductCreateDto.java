package com.shoppinglist.shoppinglist.payload.dto.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;

import java.math.BigDecimal;

public class ProductCreateDto {
    private String name;
    private UrgencyLevel urgencyLevel;
    private Long shoppingCartId;
    private BigDecimal productPrice;
    private Integer productQuantity;

    public ProductCreateDto() {
    }

    public ProductCreateDto(
            String name,
            UrgencyLevel urgencyLevel,
            Long shoppingCartId,
            BigDecimal productPrice,
            Integer productQuantity) {
        this.name = name;
        this.urgencyLevel = urgencyLevel;
        this.shoppingCartId = shoppingCartId;
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

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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
