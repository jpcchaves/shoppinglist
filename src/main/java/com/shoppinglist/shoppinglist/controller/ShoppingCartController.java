package com.shoppinglist.shoppinglist.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<List<ShoppingCartListDto>> getAll(
            @RequestParam(name = "name", required = false) String name
    ) {
        return ResponseEntity.ok(shoppingCartService.getAll(name));
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
