package com.shoppinglist.shoppinglist.factory.shoppingcart;

import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartMinDto;
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
            int productsAmount,
            Date createdAt) {
        return new ShoppingCartListDto(id, uuid, name, productsAmount
                , createdAt);
    }
}
