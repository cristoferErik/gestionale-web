package com.gestionale.web.app.gestionale_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionale.web.app.gestionale_web.models.UserEntity;
import com.gestionale.web.app.gestionale_web.models.dto.user_dto.UserDto;
import com.gestionale.web.app.gestionale_web.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body("LISTADO");
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@Valid @PathVariable Long id){
        UserEntity user = userService.getUserById(id).get();
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPasword());
        userDto.setEmail(user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPasword(userDto.getPassword());
        userEntity.setEmail(userDto.getPassword());
        userEntity.setRoles(userDto.getRoles());
        UserEntity userE = userService.save(userEntity);
        UserDto userDto2 = new UserDto();
        userDto2.setId(userE.getId());
        userDto2.setUsername(userE.getUsername());
        userDto2.setEmail(userE.getEmail());
        userDto2.setRoles(userE.getRoles());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto2);
    }
}
