package com.gestionale.web.app.gestionale_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestionale.web.app.gestionale_web.models.Backup;
import com.gestionale.web.app.gestionale_web.models.dto.BackupDto;
import com.gestionale.web.app.gestionale_web.models.model_assembler.BackupModelAssembler;
import com.gestionale.web.app.gestionale_web.services.impl.BackupServiceImpl;

@CrossOrigin(originPatterns="*")
@RequestMapping("/backups")
@RestController
public class BackupController {

    @Autowired
    private BackupServiceImpl backupServiceImpl;

    @Autowired
    private PagedResourcesAssembler<Backup> pagedResourcesAssembler;

    @Autowired
    private BackupModelAssembler backupModelAssembler;

    @GetMapping("/{webSiteId}")
    public ResponseEntity<PagedModel<BackupDto>> getAllBackupsByWebSite(
        @PathVariable Long webSiteId,
        @RequestParam(value= "page",defaultValue="0") Integer page,
        @RequestParam(value="size",defaultValue="10") Integer size
        )
    {
        System.out.println(webSiteId);
        Pageable pageable = PageRequest.of(page,size);
        Page<Backup> serviceUpdatePage = backupServiceImpl.getAllBackupsByWebSiteId(webSiteId,pageable);
        PagedModel<BackupDto> resources = pagedResourcesAssembler.toModel(serviceUpdatePage,backupModelAssembler);
        return ResponseEntity.ok(resources);
    }
}
