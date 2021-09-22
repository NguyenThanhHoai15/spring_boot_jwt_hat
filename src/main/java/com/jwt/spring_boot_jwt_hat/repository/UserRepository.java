package com.jwt.spring_boot_jwt_hat.repository;

import com.jwt.spring_boot_jwt_hat.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
