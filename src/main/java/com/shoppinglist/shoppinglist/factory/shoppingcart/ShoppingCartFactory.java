package com.shoppinglist.shoppinglist.factory.shoppingcart;

import com.shoppinglist.shoppinglist.payload.dto.product.ProductDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ShoppingCartFactory {
    ShoppingCartListDto createShoppingCart(
            Long id,
            UUID uuid,
            String name,
            String description,
            int productsAmount,
            Date createdAt);

    ShoppingCartListDto createShoppingCartWithTotalPrice(
            Long id,
            UUID uuid,
            String name,
            String description,
            List<ProductDto> productList,
            Date createdAt);
}
