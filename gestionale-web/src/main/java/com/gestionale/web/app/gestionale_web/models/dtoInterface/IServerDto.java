package com.gestionale.web.app.gestionale_web.models.dtoInterface;

//Projection using interfaces

import com.gestionale.web.app.gestionale_web.models.Customer;

//Should have getter methods for every properties of the column of the Server class
public interface IServerDto {
    public Long getId();
    public String getServerUrl();
    public String getPanelloUrl();
    public String getUserServer();
    public String getPasswordServer();
    public Customer getCustomer();
}
