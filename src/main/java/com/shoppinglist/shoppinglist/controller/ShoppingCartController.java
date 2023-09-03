package com.shoppinglist.shoppinglist.controller;

import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartRequestDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.service.usecases.ShoppingCartService;
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
    public ResponseEntity<ApiMessageResponse> create(@RequestBody ShoppingCartRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartService.create(request));
    }

    @PutMapping("/{shoppingCartId}")
    private ResponseEntity<ApiMessageResponse> update(
            @PathVariable(name = "shoppingCartId") Long id,
            @RequestBody ShoppingCartRequestDto requestDto) {
        return ResponseEntity.ok(shoppingCartService.update(id, requestDto));
    }

    @DeleteMapping("/{shoppingCartId}")
    private ResponseEntity<ApiMessageResponse> delete(@PathVariable(name = "shoppingCartId") Long shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.delete(shoppingCartId));
    }
}
