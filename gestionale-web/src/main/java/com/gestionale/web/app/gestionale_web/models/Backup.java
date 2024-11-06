package com.gestionale.web.app.gestionale_web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="backups")
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class Backup extends RecordUpdate{
    public Backup(Long id, String dateRecordUpdate, String description, WebSite webSite) {
        super(id, dateRecordUpdate, description, webSite);
    }
    public Backup() {
        super();
    }
}
