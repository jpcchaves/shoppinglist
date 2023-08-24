package com.shoppinglist.shoppinglist.service.usecases;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartMinDto;

import java.util.List;

public interface ShoppingCartService {
    ApiMessageResponse create(ShoppingCart request);

    List<ShoppingCartMinDto> getAll();
}
