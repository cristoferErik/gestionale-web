package com.gestionale.web.app.gestionale_web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Table(name="su_ws_detail")
@Entity
public class SuWsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Status{ACTIVATED,DEACTIVATED};

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String dateCreation;

    @ManyToOne
    @JoinColumn(name="service_update_id")
    private ServiceUpdate serviceUpdate;

    @ManyToOne
    @JoinColumn(name="web_site_id")
    private WebSite webSite;

    public SuWsDetail(Long id, Status status, String dateCreation) {
        this.id = id;
        this.status = status;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ServiceUpdate getServiceUpdate() {
        return serviceUpdate;
    }

    public void setServiceUpdate(ServiceUpdate serviceUpdate) {
        this.serviceUpdate = serviceUpdate;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }
    
}
