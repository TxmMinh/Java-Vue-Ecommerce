package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.AuthenticationToken;
import com.ecommerce.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    Optional<AuthenticationToken> findByUser(User user);

    AuthenticationToken findByToken(String token);
}
