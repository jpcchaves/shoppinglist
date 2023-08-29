package com.shoppinglist.shoppinglist.factory.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import org.springframework.stereotype.Component;

@Component
public class ConcreteProductFactory implements ProductFactory {
    @Override
    public Product createProduct(
            String name,
            UrgencyLevel urgencyLevel,
            ShoppingCart shoppingCart) {
        return new Product(name, urgencyLevel, shoppingCart);
    }
}
