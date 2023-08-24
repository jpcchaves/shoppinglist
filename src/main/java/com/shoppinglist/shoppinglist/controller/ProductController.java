package com.shoppinglist.shoppinglist.controller;

import com.itextpdf.text.DocumentException;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.product.ProductCreateDto;
import com.shoppinglist.shoppinglist.service.usecases.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable(name = "shoppingCartId") Long shoppingCartId) {
        return ResponseEntity.ok(productService.getProducts(shoppingCartId));
    }

    @PostMapping
    public ResponseEntity<ApiMessageResponse> createProducts(@RequestBody ProductCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(createDto));
    }


    @DeleteMapping("/{shoppingCartId}/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable(name = "shoppingCartId") Long shoppingCartId,
            @PathVariable(name = "id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.removeProduct(shoppingCartId, id));
    }

    @GetMapping("/{shoppingCartId}/export-to-pdf")
    public ResponseEntity<byte[]> getProductsListPdf(@PathVariable(name = "shoppingCartId") Long shoppingCartId) throws DocumentException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(productService.getProductsListPdf(shoppingCartId), httpHeaders, HttpStatus.OK);
    }

}
