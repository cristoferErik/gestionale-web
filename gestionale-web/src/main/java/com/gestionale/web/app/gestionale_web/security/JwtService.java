package com.gestionale.web.app.gestionale_web.security;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.gestionale.web.app.gestionale_web.models.token.dao.TokenDao;
import static com.gestionale.web.app.gestionale_web.security.TokenJwtConfig.SECRET_KEY;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtService {

    public TokenDao generateToken(String email,Collection<? extends GrantedAuthority> authorities){
        String roles= authorities
                        .stream()
                        .map(ga -> ga.getAuthority())
                        .collect(Collectors.joining(","));

        Claims claims = Jwts.claims()
        .add("authorities",roles)//<----
        .add("email",email)
        .build();
        return createToken(claims,email);
    }
    public TokenDao createToken(Map<String, Object> claims, String email) {
        TokenDao tokenDao= new TokenDao();
        Date dateExpiration=new Date(System.currentTimeMillis() + 1000 * 60 * 1);
        String jwts=Jwts.builder()
                .claims(claims)
                .subject(email)
                .signWith(SECRET_KEY)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(dateExpiration)
                .compact();
        tokenDao.setToken(jwts);
        tokenDao.setExpiration(dateExpiration);
        return tokenDao;
    }
    //qui si fa la verifica della firma,del token con la nostra chiave segretta
    //In caso non siano uguale, si inviera un exception
    public Claims extractClaims(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

}
