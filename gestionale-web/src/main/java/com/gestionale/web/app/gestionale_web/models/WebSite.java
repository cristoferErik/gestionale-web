package com.gestionale.web.app.gestionale_web.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
    private String dateCreation;

    @Column(name="next_update_backup")
    private String nextUpdateBackup;

    @Column(name="next_update_website")
    private String nextUpdateWebSite;
 
    @ManyToOne
    @JoinColumn(name="server_id",nullable = false)
    private Server server;
    
    @OneToMany(mappedBy = "webSite",cascade = CascadeType.ALL)
    private List<RecordUpdate> recordUpdates;

    @OneToMany(mappedBy= "webSite",cascade = CascadeType.ALL)
    private List<SuWsDetail> suWsDetails;
    
    public WebSite() {}

    @PrePersist
    public void prePersist(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dateCreation= LocalDateTime.now().format(formatter);
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
    public String getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Server getServer() {
        return server;
    }
    public void setServer(Server server) {
        this.server = server;
    }
    public void setRecordUpdates(List<RecordUpdate> recordUpdates) {
        this.recordUpdates = recordUpdates;
    }
    public List<RecordUpdate> getRecordUpdates() {
        return recordUpdates;
    }
    public String getNextBackup() {
        return nextUpdateBackup;
    }
    public void setNextBackup(String nextBackup) {
        this.nextUpdateBackup = nextBackup;
    }
    public String getNextUpdateWebSite() {
        return nextUpdateWebSite;
    }
    public void setNextUpdateWebSite(String nextUpdateWebSite) {
        this.nextUpdateWebSite = nextUpdateWebSite;
    }
    public String getNextUpdateBackup() {
        return nextUpdateBackup;
    }
    public void setNextUpdateBackup(String nextUpdateBackup) {
        this.nextUpdateBackup = nextUpdateBackup;
    }
    public List<SuWsDetail> getSuWsDetails() {
        return suWsDetails;
    }
    public void setSuWsDetails(List<SuWsDetail> suWsDetails) {
        this.suWsDetails = suWsDetails;
    }
    @Override
    public String toString() {
        return "WebSite [id=" + id + ", name=" + name + ", url=" + url + ", price=" + price + ", dateCreation="
                + dateCreation + ", server=" + server + ", recordUpdates="
                + recordUpdates + "]";
    }
    
}
