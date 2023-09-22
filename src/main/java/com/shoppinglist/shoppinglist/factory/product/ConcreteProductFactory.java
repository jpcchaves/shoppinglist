package com.shoppinglist.shoppinglist.factory.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConcreteProductFactory implements ProductFactory {
    @Override
    public Product createProduct(
            String name,
            UrgencyLevel urgencyLevel,
            ShoppingCart shoppingCart) {
        return new Product(name, urgencyLevel, shoppingCart);
    }

    @Override
    public Product createProductWithPrice(
            String name,
            UrgencyLevel urgencyLevel,
            ShoppingCart shoppingCart,
            BigDecimal productPrice,
            Integer productQuantity) {
        return new Product(name, productPrice, productQuantity, urgencyLevel, shoppingCart);
    }
}
