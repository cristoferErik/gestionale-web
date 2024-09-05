package com.gestionale.web.app.gestionale_web.security;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestionale.web.app.gestionale_web.models.UserEntity;
import com.gestionale.web.app.gestionale_web.models.dto.user_dto.UserAuthDto;
import com.gestionale.web.app.gestionale_web.models.dto.user_dto.UserRegisterDto;
import com.gestionale.web.app.gestionale_web.models.token.Token;
import com.gestionale.web.app.gestionale_web.models.token.dao.TokenDao;
import com.gestionale.web.app.gestionale_web.models.token.repository.TokenRepository;
import com.gestionale.web.app.gestionale_web.models.token.services.TokenService;
import com.gestionale.web.app.gestionale_web.repositories.UserRepository;
import static com.gestionale.web.app.gestionale_web.security.TokenJwtConfig.PREFIX_TOKEN;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void register(UserRegisterDto userDto) {
        UserEntity userE = new UserEntity();
        userE.setEmail(userDto.getEmail());
        userE.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userE.setRoles(userDto.getRoles());
        userRepository.save(userE);
    }

    public AuthResponse authenticate(UserAuthDto request) {
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        //Il confronto delle password si fa al'interno di authenticationManager.authenticate
        Authentication auth = authenticationManager.authenticate(upat);
        Optional<UserEntity> userEntity = userRepository.findByEmail(auth.getName());
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + auth.getName());
        }

        TokenDao tokenDao = jwtService.generateToken(auth.getName(), auth.getAuthorities());
        String jwtToken = tokenDao.getToken();
        //rifiutiamo tutti i token che non sono valiti
        revokeAllUserTokens(userEntity.get().getId());

        //Eliminiamo tutti i token che sono gia scaduti del database
        tokenService.removeTokenByDate();
        //Salvamo il token
        saveToken(userEntity.get(), jwtToken,tokenDao.getExpiration());

        AuthResponse response = new AuthResponse();
        response.setEmail(auth.getName());
        response.setMessage("Has been logged in successfully");
        response.setToken(jwtToken);
        return response;
    }

    public void logOut(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith(PREFIX_TOKEN)) {
            return;
        }
        String token = authHeader.replace(PREFIX_TOKEN, "");
        Optional<Token> storeToken= tokenRepository.findByToken(token);
        if(storeToken.isPresent()){
            storeToken.get().setRevoked(true);
            SecurityContextHolder.getContext().setAuthentication(null);
            tokenRepository.save(storeToken.get());
        }
    }
    private void revokeAllUserTokens(Long userEntityId) {
        Optional<Token> token = tokenRepository.findAllValidTokensByUser(userEntityId);
        if (token.isPresent()) {
            token.stream()
                    .map(t -> {
                        t.setRevoked(true);
                        return t;
                    })
                    .collect(Collectors.toList());
        }

    }

    public void saveToken(UserEntity userEntity, String token,Date expiration) {
        Token tk = new Token();
        tk.setUserEntity(userEntity);
        tk.setToken(token);
        tk.setRevoked(false);
        tk.setExpiration(expiration);
        tokenRepository.save(tk);
    }
}
