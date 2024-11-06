package com.gestionale.web.app.gestionale_web.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionale.web.app.gestionale_web.models.WebSite;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IWebSiteDto;

public interface WebSiteService {
    public Page<IWebSiteDto> getAllWebSiteByServer (Pageable pageable,Long id);
    public void update(Long id,WebSite webSite);
    public Optional<WebSite> getWebSiteById(Long id);
    public WebSite save(WebSite webSite);
    public Boolean delete(Long id);
}
