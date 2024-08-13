package com.gestionale.web.app.gestionale_web.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity 
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        return http.authorizeHttpRequests(
            (authz)->authz.requestMatchers("/users/**").permitAll()
            .anyRequest()
            .authenticated())
            .csrf((config)->config.disable())
            .sessionManagement((managment)->managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();//give security in our forms
    }
}
