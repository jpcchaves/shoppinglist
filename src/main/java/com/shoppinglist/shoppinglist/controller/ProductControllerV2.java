package com.shoppinglist.shoppinglist.controller;

import com.shoppinglist.shoppinglist.payload.dto.product.ProductListDtoV2;
import com.shoppinglist.shoppinglist.service.usecases.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/products")
@CrossOrigin("*")
public class ProductControllerV2 {
    private final ProductService productService;

    public ProductControllerV2(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<ProductListDtoV2> getProductsListV2(@PathVariable("shoppingCartId") Long shoppingCartId) {
        return ResponseEntity.ok(productService.productsListV2(shoppingCartId));
    }
}
