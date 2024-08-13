package com.gestionale.web.app.gestionale_web.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "record_update")
public class RecordUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="date_record_update")
    private Date dateRecordUpdate;
    private String type;
    private String description;
    @Column(name="next_update")
    private Date nextUpdate;

    @ManyToOne
    @JoinColumn(name = "web_site_id")
    private WebSite webSite;

    
    public RecordUpdate() {}

    public RecordUpdate
    (
        Long id,
        Date dateRecordUpdate,
        String type,
        String description,
        Date nextUpdate,
        WebSite webSite
    ) {
        this.id = id;
        this.dateRecordUpdate = dateRecordUpdate;
        this.type = type;
        this.description = description;
        this.nextUpdate = nextUpdate;
        this.webSite = webSite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateRecordUpdate() {
        return dateRecordUpdate;
    }

    public void setDateRecordUpdate(Date dateRecordUpdate) {
        this.dateRecordUpdate = dateRecordUpdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNextUpdate() {
        return nextUpdate;
    }

    public void setNextUpdate(Date nextUpdate) {
        this.nextUpdate = nextUpdate;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }
}
