package com.shoppinglist.shoppinglist.controller;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.payload.shoppingcart.ShoppingCartMinDto;
import com.shoppinglist.shoppinglist.service.usecases.ShoppingCartService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping-carts")
@CrossOrigin("*")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartListDto>> getAll() {
        return ResponseEntity.ok(shoppingCartService.getAll());
    }

    @PostMapping
    public ResponseEntity<ApiMessageResponse> create(@RequestBody ShoppingCart request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartService.create(request));
    }
}
