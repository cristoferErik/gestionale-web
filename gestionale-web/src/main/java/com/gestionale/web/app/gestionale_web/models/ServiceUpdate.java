package com.gestionale.web.app.gestionale_web.models;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="service_updates")
public class ServiceUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="date_update")
    private String dateUpdate;
    @Column(name="date_start")
    private Date dateStart;
    @Column(name="date_end")
    private Date dateEnd;
    private Boolean state;

    @OneToMany(mappedBy = "serviceUpdate")
    private ArrayList<WebSite> webSites;

    public ServiceUpdate() {}

    public ServiceUpdate
    (
        Long id, 
        String dateUpdate, 
        Date dateStart, 
        Date dateEnd, 
        Boolean state,
        ArrayList<WebSite> webSites

    ) {
        this.id = id;
        this.dateUpdate = dateUpdate;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.state = state;
        this.webSites = webSites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public ArrayList<WebSite> getWebSites() {
        return webSites;
    }

    public void setWebSites(ArrayList<WebSite> webSites) {
        this.webSites = webSites;
    }

    
}
