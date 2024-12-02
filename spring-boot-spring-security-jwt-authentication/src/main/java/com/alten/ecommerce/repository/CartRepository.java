package com.alten.ecommerce.repository;

import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(UserEntity user); 
}
