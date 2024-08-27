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
    
}
