package com.gestionale.web.app.gestionale_web.models;

import java.util.List;

import jakarta.persistence.CascadeType;
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
    
    private String description;

    @Column(name="period_update")
    private Integer periodUpdate;
    @Column(name="date_start")
    private String dateStart;
    @Column(name="date_end")
    private String dateEnd;


    @OneToMany(mappedBy="serviceUpdate",cascade = CascadeType.ALL)
    private List<SuWsDetail> suWsDetail;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDateStart() {
        return dateStart;
    }
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }
    public String getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
    public Integer getPeriodUpdate() {
        return periodUpdate;
    }
    public void setPeriodUpdate(Integer periodUpdate) {
        this.periodUpdate = periodUpdate;
    }
    public List<SuWsDetail> getSuWsDetail() {
        return suWsDetail;
    }
    public void setSuWsDetail(List<SuWsDetail> suWsDetail) {
        this.suWsDetail = suWsDetail;
    } 
}
