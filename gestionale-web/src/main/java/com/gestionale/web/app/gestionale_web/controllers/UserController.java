package com.gestionale.web.app.gestionale_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionale.web.app.gestionale_web.models.UserEntity;
import com.gestionale.web.app.gestionale_web.models.dto.user_dto.UserAuthDto;
import com.gestionale.web.app.gestionale_web.models.dto.user_dto.UserRegisterDto;
import com.gestionale.web.app.gestionale_web.security.AuthResponse;
import com.gestionale.web.app.gestionale_web.security.AuthService;
import static com.gestionale.web.app.gestionale_web.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.gestionale.web.app.gestionale_web.security.TokenJwtConfig.PREFIX_TOKEN;
import com.gestionale.web.app.gestionale_web.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



//@CrossOrigin(originPatterns="*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;
    
    @GetMapping
    public ResponseEntity<String> getAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body("LISTADO");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserRegisterDto> getById(@Valid @PathVariable Long id){
        UserEntity user = userService.getUserById(id).get();
        UserRegisterDto userDto = new UserRegisterDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
    @PostMapping("/register")
    public ResponseEntity<?> create(@Valid @RequestBody UserRegisterDto userRegisterDto){
        authService.register(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Register was sucessful!");
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody UserAuthDto userAuthDto){
        AuthResponse authResponse = authService.authenticate(userAuthDto);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADER_AUTHORIZATION,PREFIX_TOKEN + authResponse.getToken());
        return ResponseEntity.ok().headers(headers).body(authResponse);
    }
    

    @PostMapping("/logout")
    public void logOut(HttpServletRequest request){
        authService.logOut(request);
    }
}
