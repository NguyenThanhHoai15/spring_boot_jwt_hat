package com.jwt.spring_boot_jwt_hat.service;

import com.jwt.spring_boot_jwt_hat.entity.Token;
import com.jwt.spring_boot_jwt_hat.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService{
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token createToken(Token token) {
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public Token findTokenById(String token) {
        return tokenRepository.findTokenById(token);
    }
}
