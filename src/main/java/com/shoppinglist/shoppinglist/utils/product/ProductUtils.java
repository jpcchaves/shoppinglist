package com.shoppinglist.shoppinglist.utils.product;

import com.shoppinglist.shoppinglist.payload.dto.product.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public class ProductUtils {

    public static BigDecimal calculateShoppingCartTotalPrice(List<ProductDto> products) {
        BigDecimal totalPrice = new BigDecimal(0);

        for (ProductDto product : products) {
            totalPrice = totalPrice.add(product.getTotalPrice());
        }

        return totalPrice;
    }

}
