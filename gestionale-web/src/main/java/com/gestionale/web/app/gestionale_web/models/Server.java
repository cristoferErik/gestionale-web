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

}
