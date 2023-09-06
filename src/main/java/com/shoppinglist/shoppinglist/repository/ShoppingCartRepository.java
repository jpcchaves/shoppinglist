package com.shoppinglist.shoppinglist.repository;

import com.shoppinglist.shoppinglist.domain.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAllByOrderByCreatedAtDesc();
}
