package com.shoppinglist.shoppinglist.factory.shoppingcart;

import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.payload.dto.product.ProductDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.utils.product.ProductUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
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

    @Override
    public ShoppingCartListDto createShoppingCartWithTotalPrice(
            Long id,
            UUID uuid,
            String name,
            String description,
            List<ProductDto> productList,
            Date createdAt) {
        return new ShoppingCartListDto(id, uuid, name, description, productList.size()
                , ProductUtils.calculateShoppingCartTotalPrice(productList), createdAt);

    }
}
