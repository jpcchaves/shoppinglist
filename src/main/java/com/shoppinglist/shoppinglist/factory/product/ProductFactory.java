package com.shoppinglist.shoppinglist.factory.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;

import java.math.BigDecimal;

public interface ProductFactory {
    Product createProduct(
            String name,
            UrgencyLevel urgencyLevel,
            ShoppingCart shoppingCart);

    Product createProductWithPrice(
            String name,
            UrgencyLevel urgencyLevel,
            ShoppingCart shoppingCart,
            BigDecimal productPrice,
            Integer productQuantity
    );
}
