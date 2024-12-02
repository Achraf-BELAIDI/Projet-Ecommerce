package com.alten.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alten.ecommerce.model.UserEntity;
import com.alten.ecommerce.model.Wishlist;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUser(UserEntity user);
}
