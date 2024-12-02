package com.alten.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alten.ecommerce.model.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public Optional<UserEntity> findByEmail(String email);

}
