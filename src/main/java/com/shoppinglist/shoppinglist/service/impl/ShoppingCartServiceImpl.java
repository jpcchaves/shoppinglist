package com.shoppinglist.shoppinglist.service.impl;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartMinDto;
import com.shoppinglist.shoppinglist.repository.ShoppingCartRepository;
import com.shoppinglist.shoppinglist.service.usecases.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<ShoppingCartMinDto> getAll() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        List<ShoppingCartMinDto> shoppingCartMinDtos = new ArrayList<>();


        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCartMinDtos.add(
                    new ShoppingCartMinDto(
                            shoppingCart.getId(),
                            shoppingCart.getUuid(),
                            shoppingCart.getName()
                    )
            );
        }

        return shoppingCartMinDtos;
    }

    @Override
    public ApiMessageResponse create(ShoppingCart request) {
        ShoppingCart createdShoppingCart = shoppingCartRepository.save(request);
        return new ApiMessageResponse("Lista de compras criada com sucesso! " + createdShoppingCart.getId() + " " + createdShoppingCart.getName());
    }
}
