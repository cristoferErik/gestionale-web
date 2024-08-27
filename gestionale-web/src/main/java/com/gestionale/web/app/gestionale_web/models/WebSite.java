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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
