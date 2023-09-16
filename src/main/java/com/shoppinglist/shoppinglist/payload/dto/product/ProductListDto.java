package com.shoppinglist.shoppinglist.payload.dto.product;

import java.util.List;

public class ProductListDto {
    private String shoppingCartName;
    private List<ProductMinDto> products;

    public ProductListDto() {
    }

    public ProductListDto(
            String shoppingCartName,
            List<ProductMinDto> products) {
        this.shoppingCartName = shoppingCartName;
        this.products = products;
    }

    public String getShoppingCartName() {
        return shoppingCartName;
    }

    public void setShoppingCartName(String shoppingCartName) {
        this.shoppingCartName = shoppingCartName;
    }

    public List<ProductMinDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductMinDto> products) {
        this.products = products;
    }
}
