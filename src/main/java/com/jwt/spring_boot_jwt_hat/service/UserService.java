package com.jwt.spring_boot_jwt_hat.service;

import com.jwt.spring_boot_jwt_hat.authen.UserPrincipal;
import com.jwt.spring_boot_jwt_hat.entity.UserEntity;

public interface UserService {

    public UserEntity createUser(UserEntity user);
    public UserPrincipal findByUsername(String username);
}
