package com.gestionale.web.app.gestionale_web.services;

import java.util.Optional;

import com.gestionale.web.app.gestionale_web.models.UserEntity;

public interface UserService {
    public UserEntity save(UserEntity user);
    public Optional<UserEntity> getUserById(Long id);
    public Optional<UserEntity>findByUserName(String username);
}
