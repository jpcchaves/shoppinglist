package com.shoppinglist.shoppinglist.service.usecases;

import com.itextpdf.text.DocumentException;
import com.shoppinglist.shoppinglist.domain.entities.Product;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.product.ProductCreateDto;
import com.shoppinglist.shoppinglist.payload.dto.product.ProductListDto;
import com.shoppinglist.shoppinglist.payload.dto.product.ProductMinDto;
import com.shoppinglist.shoppinglist.payload.dto.product.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    ApiMessageResponse createProduct(ProductCreateDto createProduct);

    ProductListDto productsList(Long shoppingCartId);

    ProductListDto productsList(
            Long shoppingCartId,
            String name);

    List<Product> filterByName(
            String name,
            Long shoppingCartId);

    ApiMessageResponse updateProduct(
            Long shoppingCartId,
            Long id,
            ProductUpdateDto updateProduct);

    ProductMinDto getProductById(
            Long shoppingCartId,
            Long id);

    byte[] getProductsListPdf(Long shoppingCartId) throws DocumentException;

    ApiMessageResponse removeProduct(
            Long shoppingCartId,
            Long id);
}
