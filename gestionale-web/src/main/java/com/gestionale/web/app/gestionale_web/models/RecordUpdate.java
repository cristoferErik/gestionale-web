package com.gestionale.web.app.gestionale_web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "record_updates")
public class RecordUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="date_record_update")
    private String dateRecordUpdate;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "web_site_id")
    private WebSite webSite;

    public RecordUpdate() {
    }

    public RecordUpdate(Long id, String dateRecordUpdate, String description, WebSite webSite) {
        this.id = id;
        this.dateRecordUpdate = dateRecordUpdate;
        this.description = description;
        this.webSite = webSite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateRecordUpdate() {
        return dateRecordUpdate;
    }

    public void setDateRecordUpdate(String dateRecordUpdate) {
        this.dateRecordUpdate = dateRecordUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

    
}
