package com.gestionale.web.app.gestionale_web.models.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class WebSiteDto extends RepresentationModel<WebSiteDto>{
    private Long id;
    private String name;
    private String url;
    private float price;
    private String dateCreation;
    private Long serverId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Long getServerId() {
        return serverId;
    }
    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }
}
