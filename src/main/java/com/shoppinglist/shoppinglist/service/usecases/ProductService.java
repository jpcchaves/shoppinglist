package com.shoppinglist.shoppinglist.service.usecases;

import com.itextpdf.text.DocumentException;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.product.ProductCreateDto;

import java.util.List;

public interface ProductService {
    ApiMessageResponse createProduct(ProductCreateDto createProduct);

    List<Product> getProducts(Long shoppingCartId);

    byte[] getProductsListPdf(Long shoppingCartId) throws DocumentException;

    void removeProduct(Long id);
}
