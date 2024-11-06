package com.gestionale.web.app.gestionale_web.models.token;

import java.util.Date;

import com.gestionale.web.app.gestionale_web.models.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    private String token;

    private boolean revoked;

    private Date expiration;
    
    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    } 

}
