package com.shoppinglist.shoppinglist.service.usecases;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;

public interface ShoppingCartService {
    ApiMessageResponse create(ShoppingCart request);
}
