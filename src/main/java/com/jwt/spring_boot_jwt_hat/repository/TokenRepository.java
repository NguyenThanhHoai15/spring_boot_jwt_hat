package com.jwt.spring_boot_jwt_hat.repository;

import com.jwt.spring_boot_jwt_hat.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findTokenById(String token);
}
