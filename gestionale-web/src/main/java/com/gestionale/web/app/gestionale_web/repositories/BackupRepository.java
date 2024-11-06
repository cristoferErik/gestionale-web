package com.gestionale.web.app.gestionale_web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionale.web.app.gestionale_web.models.Backup;

@Repository
public interface BackupRepository extends JpaRepository<Backup, Long>{
    Page<Backup> findByWebSiteId(Long serverId,Pageable pageable);
}
