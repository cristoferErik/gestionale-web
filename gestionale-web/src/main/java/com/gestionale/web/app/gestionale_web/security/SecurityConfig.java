package com.gestionale.web.app.gestionale_web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtValidationFilter jwtValidationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        return http.authorizeHttpRequests(
            (authz)->authz
            .requestMatchers("/users/**","/customers/**","/servers/**","/websites/**","/serviceUpdates/**","/backups/**").permitAll()
            //.requestMatchers("").hasRole("ADMIN")
            .anyRequest()
            .authenticated())
            .addFilterBefore(jwtValidationFilter,UsernamePasswordAuthenticationFilter.class)
            .csrf((config)->config.disable())
            .sessionManagement((managment)->managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    } 
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
