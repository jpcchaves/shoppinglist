package com.shoppinglist.shoppinglist.service.impl;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.exception.ResourceNotFoundException;
import com.shoppinglist.shoppinglist.factory.shoppingcart.ShoppingCartFactory;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartCreateDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.repository.ShoppingCartRepository;
import com.shoppinglist.shoppinglist.service.usecases.ShoppingCartService;
import com.shoppinglist.shoppinglist.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartFactory shoppingCartFactory;
    private final MapperUtils mapperUtils;

    public ShoppingCartServiceImpl(
            ShoppingCartRepository shoppingCartRepository,
            ShoppingCartFactory shoppingCartFactory,
            MapperUtils mapperUtils) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartFactory = shoppingCartFactory;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<ShoppingCartListDto> getAll() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();

        return buildShoppingCartList(shoppingCarts);
    }


    @Override
    public ApiMessageResponse create(ShoppingCartCreateDto request) {
        ShoppingCart createdShoppingCart = shoppingCartRepository.save(mapperUtils.parseObject(request, ShoppingCart.class));
        return new ApiMessageResponse("Lista de compras criada com sucesso! " + createdShoppingCart.getId() + " " + createdShoppingCart.getName());
    }

    @Override
    public ApiMessageResponse delete(Long shoppingCartId) {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findById(shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista de compras não encontrada com o ID informado: " + shoppingCartId));

        shoppingCartRepository.delete(shoppingCart);

        return new ApiMessageResponse("Lista de compras deletada com sucesso!");
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
                        shoppingCart.getDescription(),
                        shoppingCart.getProducts().size(),
                        shoppingCart.getCreatedAt()
                );
    }
}
