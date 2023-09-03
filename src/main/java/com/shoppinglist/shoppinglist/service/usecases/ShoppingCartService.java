package com.shoppinglist.shoppinglist.service.usecases;

import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartCreateDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;

import java.util.List;

public interface ShoppingCartService {
    ApiMessageResponse create(ShoppingCartCreateDto request);

    List<ShoppingCartListDto> getAll();

    ApiMessageResponse delete(Long shoppingCartId);
}
