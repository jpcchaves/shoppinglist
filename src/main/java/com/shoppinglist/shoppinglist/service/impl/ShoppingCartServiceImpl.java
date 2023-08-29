package com.shoppinglist.shoppinglist.service.impl;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.factory.shoppingcart.ShoppingCartFactory;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartMinDto;
import com.shoppinglist.shoppinglist.repository.ShoppingCartRepository;
import com.shoppinglist.shoppinglist.service.usecases.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartFactory shoppingCartFactory;

    public ShoppingCartServiceImpl(
            ShoppingCartRepository shoppingCartRepository,
            ShoppingCartFactory shoppingCartFactory) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartFactory = shoppingCartFactory;
    }

    @Override
    public List<ShoppingCartListDto> getAll() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();

        return buildShoppingCartList(shoppingCarts);
    }


    @Override
    public ApiMessageResponse create(ShoppingCart request) {
        ShoppingCart createdShoppingCart = shoppingCartRepository.save(request);
        return new ApiMessageResponse("Lista de compras criada com sucesso! " + createdShoppingCart.getId() + " " + createdShoppingCart.getName());
    }

    private List<ShoppingCartListDto> buildShoppingCartList(List<ShoppingCart> shoppingCarts) {
        List<ShoppingCartListDto> shoppingCartListDtos = new ArrayList<>();

        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCartListDtos.add(buildNewShoppingCart(shoppingCart));
        }

        return shoppingCartListDtos;
    }
    
    private ShoppingCartListDto buildNewShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartFactory
                .createShoppingCart(
                        shoppingCart.getId(),
                        shoppingCart.getUuid(),
                        shoppingCart.getName(),
                        shoppingCart.getProducts().size(),
                        shoppingCart.getCreatedAt()
                );
    }
}
