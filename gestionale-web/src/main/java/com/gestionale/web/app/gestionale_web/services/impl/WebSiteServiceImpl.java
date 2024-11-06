package com.gestionale.web.app.gestionale_web.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestionale.web.app.gestionale_web.exceptions.CustomException;
import com.gestionale.web.app.gestionale_web.models.Server;
import com.gestionale.web.app.gestionale_web.models.WebSite;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IWebSiteDto;
import com.gestionale.web.app.gestionale_web.repositories.WebSiteRepository;
import com.gestionale.web.app.gestionale_web.services.ServerService;
import com.gestionale.web.app.gestionale_web.services.WebSiteService;

@Service
public class WebSiteServiceImpl implements WebSiteService {

    @Autowired
    private WebSiteRepository webSiteRepository;

    @Autowired
    private ServerService serverService;

    @Override
    public Page<IWebSiteDto> getAllWebSiteByServer(Pageable pageable, Long id) {
        Server server = serverService.getServerById(id).get();
        Page<IWebSiteDto> servers = webSiteRepository.findByServerId(server.getId(), pageable);
        return servers;
    }

    @Override
    public void update(Long id, WebSite webSite) {
        try {
            if (getWebSiteById(id).isPresent()) {
                save(webSite);
            }
        } catch (Exception e) {
            throw new CustomException("It is not possible update webSite with ID: !" + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<WebSite> getWebSiteById(Long id) {
        Optional<WebSite> webSite = webSiteRepository.findById(id);
        if (webSite.isPresent()) {
            return webSite;
        } else {
            throw new CustomException("WebSite with ID " + id + " was not found!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public WebSite save(WebSite webSite) {
        return webSiteRepository.save(webSite);
    }

    @Override
    public Boolean delete(Long id) {
        if(getWebSiteById(id).isPresent()){
            webSiteRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
