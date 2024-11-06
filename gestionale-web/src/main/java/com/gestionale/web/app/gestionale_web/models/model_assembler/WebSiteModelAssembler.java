package com.gestionale.web.app.gestionale_web.models.model_assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gestionale.web.app.gestionale_web.controllers.WebSiteController;
import com.gestionale.web.app.gestionale_web.models.dto.WebSiteDto;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IWebSiteDto;

@Component
public class WebSiteModelAssembler extends RepresentationModelAssemblerSupport<IWebSiteDto,WebSiteDto>{

    public WebSiteModelAssembler() {
        super(WebSiteController.class, WebSiteDto.class);
    }

    
    @Override
    public WebSiteDto toModel(IWebSiteDto iWebSiteDto) {
        WebSiteDto webSiteDto = new WebSiteDto();
        webSiteDto.setId(iWebSiteDto.getId());
        webSiteDto.setName(iWebSiteDto.getName());
        webSiteDto.setUrl(iWebSiteDto.getUrl());
        webSiteDto.setPrice(iWebSiteDto.getPrice());
        webSiteDto.setDateCreation(iWebSiteDto.getDateCreation());
        webSiteDto.setServerId(iWebSiteDto.getId());

        return webSiteDto;
    }

}
