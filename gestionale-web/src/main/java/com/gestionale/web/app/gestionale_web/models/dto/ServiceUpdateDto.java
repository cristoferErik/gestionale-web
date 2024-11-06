package com.gestionale.web.app.gestionale_web.models.dto;

import org.springframework.hateoas.RepresentationModel;

public class ServiceUpdateDto extends RepresentationModel<ServiceUpdateDto>{
    private Long id;
    private String description;
    private Integer periodUpdate;
    private String dateStart;
    private String dateEnd;


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
}
