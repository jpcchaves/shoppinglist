package com.shoppinglist.shoppinglist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.exception.ResourceNotFoundException;
import com.shoppinglist.shoppinglist.factory.shoppingcart.ShoppingCartFactory;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartRequestDto;
import com.shoppinglist.shoppinglist.repository.ShoppingCartRepository;
import com.shoppinglist.shoppinglist.service.usecases.ShoppingCartService;
import com.shoppinglist.shoppinglist.utils.mapper.MapperUtils;

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
    public List<ShoppingCartListDto> getAll(String name) {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        if (isNull(name)) {
            shoppingCarts.addAll(fetchShoppingCarts());

            return buildShoppingCartList(shoppingCarts);
        }

        shoppingCarts.addAll(shoppingCartRepository.findByNameContainingIgnoreCase(name));
        return buildShoppingCartList(shoppingCarts);
    }

    @Override
    public ShoppingCartListDto getById(Long shoppingCartId) {
        ShoppingCart shoppingCart = fetchShoppingCart(shoppingCartId);

        return mapperUtils.parseObject(shoppingCart, ShoppingCartListDto.class);
    }

    @Override
    public ApiMessageResponse create(ShoppingCartRequestDto request) {
        ShoppingCart createdShoppingCart = shoppingCartRepository
                .save(mapperUtils.parseObject(request, ShoppingCart.class));
        return new ApiMessageResponse("Lista de compras criada com sucesso! " + createdShoppingCart.getId() + " "
                + createdShoppingCart.getName());
    }

    @Override
    public ApiMessageResponse update(
            Long shoppingCartId,
            ShoppingCartRequestDto request) {
        ShoppingCart shoppingCart = fetchShoppingCart(shoppingCartId);
        shoppingCart.setName(request.getName());
        shoppingCart.setDescription(request.getDescription());

        shoppingCartRepository.save(shoppingCart);

        return new ApiMessageResponse("Lista de compras atualizada com sucesso!");
    }

    @Override
    public ApiMessageResponse delete(Long shoppingCartId) {
        ShoppingCart shoppingCart = fetchShoppingCart(shoppingCartId);

        shoppingCartRepository.delete(shoppingCart);

        return new ApiMessageResponse("Lista de compras deletada com sucesso!");
    }

    @Override
    public List<ShoppingCartListDto> filterByName(String name) {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findByNameContainingIgnoreCase(name);

        return mapperUtils.parseListObjects(shoppingCarts, ShoppingCartListDto.class);
    }

    private List<ShoppingCart> fetchShoppingCarts() {
        return shoppingCartRepository.findAllByOrderByCreatedAtDesc();
    }

    private ShoppingCart fetchShoppingCart(Long shoppingCartId) {
        return shoppingCartRepository
                .findById(shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Lista de compras não encontrada com o ID informado: " + shoppingCartId));
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
                        shoppingCart.getCreatedAt());
    }

    private boolean isNull(String param) {
        return param == null;
    }
}
