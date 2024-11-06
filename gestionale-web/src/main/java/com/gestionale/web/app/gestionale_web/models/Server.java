package com.gestionale.web.app.gestionale_web.models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "servers")
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "server_url")
    private String serverUrl;
    @Column(name = "panello_url")
    private String panelloUrl;
    @Column(name = "user_server")
    private String userServer;
    @Column(name = "password_server")
    private String passwordServer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="customer_id",nullable=false)
    private Customer customer;

    @OneToMany(mappedBy = "server",cascade = CascadeType.ALL)
    private List<WebSite> webSites;

    @Override
    public String toString() {
        return "Server [id=" + id + ", serverUrl=" + serverUrl + ", panelloUrl=" + panelloUrl + ", userServer="
                + userServer + ", passwordServer=" + passwordServer + ", customer=" + customer + ", webSites="
                + webSites + "]";
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<WebSite> getWebSites() {
        return webSites;
    }

    public void setWebSites(List<WebSite> webSites) {
        this.webSites = webSites;
    }

    
}
