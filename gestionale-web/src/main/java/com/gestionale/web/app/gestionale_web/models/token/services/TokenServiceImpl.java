package com.gestionale.web.app.gestionale_web.models.token.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionale.web.app.gestionale_web.models.token.repository.TokenRepository;

import jakarta.transaction.Transactional;

@Service
public class TokenServiceImpl implements  TokenService{

    @Autowired
    private TokenRepository tokenRepository;

    @Transactional
    @Override
    public void removeTokenByDate() {
        tokenRepository.deleteTokensByDate(new Date(System.currentTimeMillis()));
    }
    
}
