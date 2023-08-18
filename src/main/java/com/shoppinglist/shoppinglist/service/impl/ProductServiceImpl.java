package com.shoppinglist.shoppinglist.service.impl;

import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.repository.ProductRepository;
import com.shoppinglist.shoppinglist.service.usecases.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
