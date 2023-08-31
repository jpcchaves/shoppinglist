package com.shoppinglist.shoppinglist.factory.shoppingcart;

import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class ConcreteShoppingCartFactory implements ShoppingCartFactory {
    @Override
    public ShoppingCartListDto createShoppingCart(
            Long id,
            UUID uuid,
            String name,
            String description,
            int productsAmount,
            Date createdAt) {
        return new ShoppingCartListDto(id, uuid, name, description, productsAmount
                , createdAt);
    }
}
