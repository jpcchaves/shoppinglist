package com.shoppinglist.shoppinglist.service.usecases;

import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartRequestDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;

import java.util.List;

public interface ShoppingCartService {
    ApiMessageResponse create(ShoppingCartRequestDto request);

    List<ShoppingCartListDto> getAll();

    ApiMessageResponse update(
            Long shoppingCartId,
            ShoppingCartRequestDto request);

    ApiMessageResponse delete(Long shoppingCartId);
}
