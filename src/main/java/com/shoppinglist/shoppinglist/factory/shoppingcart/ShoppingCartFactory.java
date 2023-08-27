package com.shoppinglist.shoppinglist.factory.shoppingcart;

import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartMinDto;

import java.util.UUID;

public interface ShoppingCartFactory {
    ShoppingCartMinDto createShoppingCart(
            Long id,
            UUID uuid,
            String name);
}
