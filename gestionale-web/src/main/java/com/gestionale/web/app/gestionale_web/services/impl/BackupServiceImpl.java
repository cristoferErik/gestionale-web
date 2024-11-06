package com.gestionale.web.app.gestionale_web.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestionale.web.app.gestionale_web.models.Backup;
import com.gestionale.web.app.gestionale_web.repositories.BackupRepository;
import com.gestionale.web.app.gestionale_web.services.BackupService;

@Service
public class BackupServiceImpl implements BackupService{

    @Autowired
    private BackupRepository backupRepository;

    @Override
    public Page<Backup> getAllBackupsByWebSiteId(Long id,Pageable pageable) {
      Page<Backup> backups  =this.backupRepository.findByWebSiteId(id, pageable);
      return backups;
    }

    @Override
    public void updateBackup(Backup customer, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Backup> getBackupById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Backup saveBackup(Backup customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean deleteBackup(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
