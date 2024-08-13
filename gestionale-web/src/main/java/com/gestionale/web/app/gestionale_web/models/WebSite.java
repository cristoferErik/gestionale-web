package com.gestionale.web.app.gestionale_web.models;

import java.util.ArrayList;
import java.util.Date;

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
@Table(name="web_sites")
public class WebSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private Float price;
    @Column(name = "date_creation")
    private Date dateCreation;
    @ManyToOne
    @JoinColumn(name="server_id",nullable = false)
    private Server server;
    
    @ManyToOne
    @JoinColumn(name="service_update_id",nullable=true)
    private ServiceUpdate serviceUpdate;
    
    @OneToMany(mappedBy = "webSite",cascade = CascadeType.ALL)
    private ArrayList<RecordUpdate> recordUpdates;

    
    public WebSite() {}

    public WebSite
    (
        Long id,
        String name,
        String url,
        Float price,
        Date dateCreation,
        Server server,
        ServiceUpdate serviceUpdate,
        ArrayList<RecordUpdate> recordUpdates
    ) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;
        this.dateCreation = dateCreation;
        this.server = server;
        this.serviceUpdate = serviceUpdate;
        this.recordUpdates = recordUpdates;
    }

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public ServiceUpdate getServiceUpdate() {
        return serviceUpdate;
    }

    public void setServiceUpdate(ServiceUpdate serviceUpdate) {
        this.serviceUpdate = serviceUpdate;
    }

    public ArrayList<RecordUpdate> getRecordUpdates() {
        return recordUpdates;
    }

    public void setRecordUpdates(ArrayList<RecordUpdate> recordUpdates) {
        this.recordUpdates = recordUpdates;
    }

    
}
