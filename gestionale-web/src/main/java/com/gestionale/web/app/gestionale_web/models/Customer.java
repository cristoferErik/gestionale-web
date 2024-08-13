package com.gestionale.web.app.gestionale_web.models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String city;
    private String address;
    private String email;
    private String cellphone;

    @JsonManagedReference 
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Server> servers;
    
    public Customer() {}

    public Customer
        (
            Long id,
            String name,
            String lastName,
            String city,
            String address,
            String email,
            String cellphone
        ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.email = email;
        this.cellphone = cellphone;
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
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCellphone() {
        return cellphone;
    }
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    public List<Server> getServers() {
        return servers;
    }
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
