package com.shoppinglist.shoppinglist.repository;

import com.shoppinglist.shoppinglist.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByShoppingCart_Id(Long shoppingCartId);
}
