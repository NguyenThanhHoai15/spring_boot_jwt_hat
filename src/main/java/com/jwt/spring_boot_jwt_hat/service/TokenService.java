package com.jwt.spring_boot_jwt_hat.service;

import com.jwt.spring_boot_jwt_hat.entity.Token;


public interface TokenService {
    Token createToken(Token token);

    Token findTokenById(String token);
}
