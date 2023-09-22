package com.shoppinglist.shoppinglist.payload.dto.product;

import java.math.BigDecimal;
import java.util.List;

public class ProductListDtoV2 {
    private String shoppingCartName;
    private List<ProductDto> products;
    private BigDecimal shoppingListTotalPrice;

    public ProductListDtoV2() {
    }

    public ProductListDtoV2(
            String shoppingCartName,
            List<ProductDto> products,
            BigDecimal shoppingListTotalPrice) {
        this.shoppingCartName = shoppingCartName;
        this.products = products;
        this.shoppingListTotalPrice = shoppingListTotalPrice;
    }

    public String getShoppingCartName() {
        return shoppingCartName;
    }

    public void setShoppingCartName(String shoppingCartName) {
        this.shoppingCartName = shoppingCartName;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public BigDecimal getShoppingListTotalPrice() {
        return shoppingListTotalPrice;
    }

    public void setShoppingListTotalPrice(BigDecimal shoppingListTotalPrice) {
        this.shoppingListTotalPrice = shoppingListTotalPrice;
    }
}
