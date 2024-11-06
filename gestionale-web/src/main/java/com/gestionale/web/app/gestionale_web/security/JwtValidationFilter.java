package com.gestionale.web.app.gestionale_web.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionale.web.app.gestionale_web.models.token.repository.TokenRepository;
import static com.gestionale.web.app.gestionale_web.security.TokenJwtConfig.CONTENT_TYPE;
import static com.gestionale.web.app.gestionale_web.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.gestionale.web.app.gestionale_web.security.TokenJwtConfig.PREFIX_TOKEN;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtValidationFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HEADER_AUTHORIZATION);
    
        if(header==null|| !header.startsWith(PREFIX_TOKEN)){
            filterChain.doFilter(request,response);
            return;
        }
        String token= header.replace(PREFIX_TOKEN,"");
        try{
            /*-----------------qui si valida il token--------------------- */
            Claims claims = jwtService.extractClaims(token);
            boolean tk = tokenRepository.findByToken(token)
                                .map(t->t.isRevoked())
                                .orElse(false);
            if(tk){
                filterChain.doFilter(request,response);
                return;
            }
            /*------------------------------------------------------------ */
            String email =  claims.getSubject();
            String authorities = claims.get("authorities",String.class);
            List<String> listAuthorities= new ArrayList<>(Arrays.asList(authorities.split(",")));
            Collection<? extends GrantedAuthority> grantedAuthorities = listAuthorities
            .stream()
            .map(sa-> new SimpleGrantedAuthority(sa))
            .collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,null,grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }catch(JwtException e){
            Map<String,String> body = new HashMap<>();
            body.put("error",e.getMessage());
            body.put("message","Token is not valid!");
            //Questo ci permette trasformare al tipo json string
            String jsonBody = new ObjectMapper().writeValueAsString(body);
            response.getWriter().write(jsonBody);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
        }
    }
}
