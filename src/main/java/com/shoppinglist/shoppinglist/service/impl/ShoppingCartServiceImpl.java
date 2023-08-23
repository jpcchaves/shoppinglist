package com.shoppinglist.shoppinglist.service.impl;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.repository.ShoppingCartRepository;
import com.shoppinglist.shoppinglist.service.usecases.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ApiMessageResponse create(ShoppingCart request) {
        ShoppingCart createdShoppingCart = shoppingCartRepository.save(request);
        return new ApiMessageResponse("Lista de compras criada com sucesso! " + createdShoppingCart.getId() + " " + createdShoppingCart.getName());
    }
}
