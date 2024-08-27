package com.gestionale.web.app.gestionale_web.models.dto.user_dto;

import java.util.List;

import com.gestionale.web.app.gestionale_web.models.Role;

public class UserRegisterDto {
    private Long id;
    private String password;
    private String email;
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles=roles;
    }
}
