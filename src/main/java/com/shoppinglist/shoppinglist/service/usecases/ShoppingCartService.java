package com.shoppinglist.shoppinglist.service.usecases;

import java.util.List;

import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartRequestDto;

public interface ShoppingCartService {
    ApiMessageResponse create(ShoppingCartRequestDto request);

    List<ShoppingCartListDto> getAll();

    ShoppingCartListDto getById(Long shoppingCartId);

    ApiMessageResponse update(
            Long shoppingCartId,
            ShoppingCartRequestDto request);

    ApiMessageResponse delete(Long shoppingCartId);
}
