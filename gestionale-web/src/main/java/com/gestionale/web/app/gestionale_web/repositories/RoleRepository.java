package com.gestionale.web.app.gestionale_web.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionale.web.app.gestionale_web.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
