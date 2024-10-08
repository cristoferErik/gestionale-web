package com.gestionale.web.app.gestionale_web.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestionale.web.app.gestionale_web.exceptions.CustomException;
import com.gestionale.web.app.gestionale_web.models.UserEntity;
import com.gestionale.web.app.gestionale_web.repositories.UserRepository;
import com.gestionale.web.app.gestionale_web.services.UserService;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly=true)
    public Optional<UserEntity> getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user;
        } else {
            throw new CustomException(" User with ID " + id + " was not found! ",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmail(String email) {
        Optional<UserEntity> user1 = userRepository.findByEmail(email);
        return user1;
    }

    @Override
    @Transactional(readOnly=true)
    public boolean existsByEmail(String username) {
        return userRepository.existsByEmail(username);
    }

}
