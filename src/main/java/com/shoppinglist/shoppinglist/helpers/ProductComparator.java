package com.shoppinglist.shoppinglist.helpers;

import com.shoppinglist.shoppinglist.domain.Enum.UrgencyLevel;
import com.shoppinglist.shoppinglist.domain.entities.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(
            Product product1,
            Product product2) {
        UrgencyLevel level1 = product1.getUrgencyLevel();
        UrgencyLevel level2 = product2.getUrgencyLevel();

        return Integer.compare(level1.ordinal(), level2.ordinal());
    }
}
