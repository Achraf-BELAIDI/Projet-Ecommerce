package com.alten.ecommerce.repository;

import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.CartItem;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByProductIdAndCartUserEmail(Long productId, String email);
}
