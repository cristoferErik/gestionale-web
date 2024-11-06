package com.gestionale.web.app.gestionale_web.models.dtoInterface;

import java.util.Date;

import com.gestionale.web.app.gestionale_web.models.Server;

public interface IWebSiteDto {
    public Long getId();
    public String getName();
    public String getUrl();
    public Float getPrice();
    public String getDateCreation();
    public Server getServer();
}
