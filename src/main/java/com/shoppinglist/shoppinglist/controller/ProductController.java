package com.shoppinglist.shoppinglist.controller;

import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.service.usecases.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Product> createProducts(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }
}
