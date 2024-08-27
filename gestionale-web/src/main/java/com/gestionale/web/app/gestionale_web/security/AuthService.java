package com.gestionale.web.app.gestionale_web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestionale.web.app.gestionale_web.models.UserEntity;
import com.gestionale.web.app.gestionale_web.models.dto.user_dto.UserAuthDto;
import com.gestionale.web.app.gestionale_web.models.dto.user_dto.UserRegisterDto;
import com.gestionale.web.app.gestionale_web.repositories.UserRepository;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void register(UserRegisterDto userDto) {
        UserEntity userE= new UserEntity();
        userE.setEmail(userDto.getEmail());
        userE.setPassword(passwordEncoder.encode(userDto.getPassword()) );
        userE.setRoles(userDto.getRoles());
        userRepository.save(userE);
    }

    public AuthResponse authenticate(UserAuthDto request){
        UsernamePasswordAuthenticationToken upat= new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        //Il confronto delle password si fa al'interno di authenticationManager.authenticate
        Authentication auth = authenticationManager.authenticate(upat);
        String jwtToken = jwtService.generateToken(auth.getName(),auth.getAuthorities());
        AuthResponse response = new AuthResponse();
        response.setEmail(auth.getName());
        response.setMessage("Has been logged in successfully");
        response.setToken(jwtToken);
        return response;
    }

}
