package com.shoppinglist.shoppinglist.service.usecases;

import com.itextpdf.text.DocumentException;
import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import com.shoppinglist.shoppinglist.payload.dto.product.ProductCreateDto;
import com.shoppinglist.shoppinglist.payload.dto.product.ProductMinDto;
import com.shoppinglist.shoppinglist.payload.dto.product.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    ApiMessageResponse createProduct(ProductCreateDto createProduct);

    List<ProductMinDto> getProducts(Long shoppingCartId);

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
