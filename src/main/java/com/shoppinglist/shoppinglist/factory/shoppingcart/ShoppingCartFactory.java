package com.shoppinglist.shoppinglist.factory.shoppingcart;

import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartMinDto;

import java.util.Date;
import java.util.UUID;

public interface ShoppingCartFactory {
    ShoppingCartListDto createShoppingCart(
            Long id,
            UUID uuid,
            String name,
            int productsAmount,
            Date createdAt);
}
