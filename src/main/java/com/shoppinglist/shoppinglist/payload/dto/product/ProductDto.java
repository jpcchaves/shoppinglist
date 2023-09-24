package com.shoppinglist.shoppinglist.payload.dto.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ProductDto {
    private Long id;
    private String name;
    private UrgencyLevel urgencyLevel;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private BigDecimal totalPrice;

    public ProductDto() {
    }

    public ProductDto(
            Long id,
            String name,
            UrgencyLevel urgencyLevel,
            BigDecimal productPrice,
            Integer productQuantity,
            BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.urgencyLevel = urgencyLevel;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
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

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = this.productPrice.multiply(BigDecimal.valueOf(getProductQuantity()));
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
