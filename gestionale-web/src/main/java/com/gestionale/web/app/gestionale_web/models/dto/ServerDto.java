package com.gestionale.web.app.gestionale_web.models.dto;

import org.springframework.hateoas.RepresentationModel;

public class ServerDto extends  RepresentationModel<ServerDto>{

    private Long id;
    private String serverUrl;
    private String panelloUrl;
    private String userServer;
    private String passwordServer;
    private Long customer;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getServerUrl() {
        return serverUrl;
    }
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
    public String getPanelloUrl() {
        return panelloUrl;
    }
    public void setPanelloUrl(String panelloUrl) {
        this.panelloUrl = panelloUrl;
    }
    public String getUserServer() {
        return userServer;
    }
    public void setUserServer(String userServer) {
        this.userServer = userServer;
    }
    public String getPasswordServer() {
        return passwordServer;
    }
    public void setPasswordServer(String passwordServer) {
        this.passwordServer = passwordServer;
    }
    public Long getCustomer() {
        return customer;
    }
    public void setCustomer(Long customerId) {
        this.customer = customerId;
    }
}
