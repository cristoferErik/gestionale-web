package com.gestionale.web.app.gestionale_web.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionale.web.app.gestionale_web.models.Backup;

public interface BackupService {
    public Page<Backup> getAllBackupsByWebSiteId(Long id,Pageable pageable);
    public void updateBackup(Backup customer,Long id);
    public Optional<Backup> getBackupById(Long id);
    public Backup saveBackup(Backup customer);
    public Boolean deleteBackup(Long id);
}
