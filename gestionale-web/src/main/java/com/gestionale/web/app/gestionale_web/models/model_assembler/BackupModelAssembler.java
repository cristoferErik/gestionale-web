package com.gestionale.web.app.gestionale_web.models.model_assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gestionale.web.app.gestionale_web.controllers.BackupController;
import com.gestionale.web.app.gestionale_web.models.Backup;
import com.gestionale.web.app.gestionale_web.models.dto.BackupDto;

@Component
public class BackupModelAssembler extends RepresentationModelAssemblerSupport<Backup,BackupDto>{

    public BackupModelAssembler() {
        super(BackupController.class, BackupDto.class);
    }

    @Override
    public BackupDto toModel(Backup backup) {
        BackupDto backupDto = new BackupDto();
        backupDto.setId(backup.getId());
        backupDto.setDescription(backup.getDescription());
        backupDto.setDateRecordUpdate(backup.getDateRecordUpdate());
        backupDto.setWebSite(backup.getWebSite().getId());
        return backupDto;
    }
}
