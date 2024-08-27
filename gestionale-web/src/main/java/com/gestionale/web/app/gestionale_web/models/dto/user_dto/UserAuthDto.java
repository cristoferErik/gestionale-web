package com.gestionale.web.app.gestionale_web.models.dto.user_dto;

public class UserAuthDto {
    private String password;
    private String email;

    public UserAuthDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
}
