package com.shoppinglist.shoppinglist.factory.shoppingcart;

import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartMinDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConcreteShoppingCartFactory implements ShoppingCartFactory {
    @Override
    public ShoppingCartMinDto createShoppingCart(
            Long id,
            UUID uuid,
            String name) {
        return new ShoppingCartMinDto(id, uuid, name);
    }
}
