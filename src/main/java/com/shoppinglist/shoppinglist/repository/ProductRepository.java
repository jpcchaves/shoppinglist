package com.shoppinglist.shoppinglist.repository;

import com.shoppinglist.shoppinglist.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByShoppingCart_Id(Long shoppingCartId);

    Optional<Product> findByIdAndShoppingCart_id(
            Long productId,
            Long shoppingCartId);

    List<Product> findByNameContainingIgnoreCase(String name);
}
