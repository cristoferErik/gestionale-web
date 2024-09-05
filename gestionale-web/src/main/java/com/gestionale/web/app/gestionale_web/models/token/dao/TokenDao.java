package com.gestionale.web.app.gestionale_web.models.token.dao;

import java.util.Date;

public class TokenDao {
    private String token;
    private Date expiration;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Date getExpiration() {
        return expiration;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    
}
