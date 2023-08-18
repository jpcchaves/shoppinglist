package com.shoppinglist.shoppinglist.service.usecases;

import com.shoppinglist.shoppinglist.domain.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getProducts();
}
