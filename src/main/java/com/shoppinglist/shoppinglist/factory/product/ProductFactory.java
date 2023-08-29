package com.shoppinglist.shoppinglist.factory.product;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;

public interface ProductFactory {
    Product createProduct(
            String name,
            UrgencyLevel urgencyLevel,
            ShoppingCart shoppingCart);
}
