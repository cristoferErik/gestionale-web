package com.gestionale.web.app.gestionale_web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionale.web.app.gestionale_web.models.Software;

public interface SoftwareRepository extends JpaRepository<Software, Long>{
    Page<Software> findByWebSiteId(Long serverId,Pageable pageable);
}
