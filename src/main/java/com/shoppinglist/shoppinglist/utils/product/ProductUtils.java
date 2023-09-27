package com.shoppinglist.shoppinglist.utils.product;

import com.shoppinglist.shoppinglist.payload.dto.product.ProductDto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductUtils {

    public static BigDecimal calculateShoppingCartTotalPrice(List<ProductDto> products) {
        BigDecimal totalPrice = new BigDecimal(0);

        for (ProductDto product : products) {
            totalPrice = totalPrice.add(product.getTotalPrice());
        }

        return totalPrice;
    }

    public static String normalizeCurrency(BigDecimal rawPrice) {
        Locale brazilLocale = new Locale
                .Builder()
                .setLanguage("pt")
                .setRegion("BR")
                .build();

        return NumberFormat.getCurrencyInstance(brazilLocale).format(rawPrice);
    }

    public static String normalizeCurrency(
            String lang,
            String region,
            BigDecimal rawPrice) {
        Locale brazilLocale = new Locale
                .Builder()
                .setLanguage(lang)
                .setRegion(region)
                .build();

        return NumberFormat.getCurrencyInstance(brazilLocale).format(rawPrice);
    }

    public static String getTotalPriceNormalized(List<ProductDto> products) {
        return normalizeCurrency(calculateShoppingCartTotalPrice(products));
    }

}
