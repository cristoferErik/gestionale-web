package com.gestionale.web.app.gestionale_web.models.dto;

import org.springframework.hateoas.RepresentationModel;

public class BackupDto extends RepresentationModel<BackupDto>{
    private Long id;
    private String dateRecordUpdate;
    private String description;
    private String url;
    private Long webSite;
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
    public Long getWebSite() {
        return webSite;
    }
    public void setWebSite(Long webSite) {
        this.webSite = webSite;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
