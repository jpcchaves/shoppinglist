package com.shoppinglist.shoppinglist.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartListDto;
import com.shoppinglist.shoppinglist.payload.dto.shoppingcart.ShoppingCartRequestDto;
import com.shoppinglist.shoppinglist.service.usecases.ShoppingCartService;

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
    public ResponseEntity<ApiMessageResponse> create(@Valid @RequestBody ShoppingCartRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartService.create(request));
    }

    @GetMapping("/{shoppingCartId}")
    private ResponseEntity<ShoppingCartListDto> getById(
            @PathVariable(name = "shoppingCartId") Long id) {
        return ResponseEntity.ok(shoppingCartService.getById(id));
    }

    @PutMapping("/{shoppingCartId}")
    private ResponseEntity<ApiMessageResponse> update(
            @PathVariable(name = "shoppingCartId") Long id,
            @Valid @RequestBody ShoppingCartRequestDto requestDto) {
        return ResponseEntity.ok(shoppingCartService.update(id, requestDto));
    }

    @DeleteMapping("/{shoppingCartId}")
    private ResponseEntity<ApiMessageResponse> delete(@PathVariable(name = "shoppingCartId") Long shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.delete(shoppingCartId));
    }
}
