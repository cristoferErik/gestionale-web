package com.gestionale.web.app.gestionale_web.services;

import java.util.Optional;

import com.gestionale.web.app.gestionale_web.models.UserEntity;

public interface UserService {
    public Optional<UserEntity> getUserById(Long id);
    public Optional<UserEntity>findByEmail(String username);
    public boolean existsByEmail(String username);
}
