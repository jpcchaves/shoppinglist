package com.shoppinglist.shoppinglist.utils.product;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTableHeaders {
    final String PRODUCTS_HEADER_NAME = "Produtos";
    final String URGENCY_HEADER_NAME = "Urgência";
    final String PRODUCT_QUANTITY_HEADER = "Quantitidade";
    final String PRODUCT_PRICE_HEADER = "Preço";
    final String PRODUCT_TOTAL_PRICE = "Total";

    public ProductTableHeaders() {
    }

    public List<String> getProductTableHeaders() {
        List<String> headerList = new ArrayList<>();

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isFinal(field.getModifiers())
                    && field.getType() == String.class) {
                try {
                    headerList.add((String) field.get(this));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return headerList;
    }
}
