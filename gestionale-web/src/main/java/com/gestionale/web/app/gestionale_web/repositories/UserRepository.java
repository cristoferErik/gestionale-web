package com.gestionale.web.app.gestionale_web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionale.web.app.gestionale_web.models.UserEntity;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByUsername(String username);
}
