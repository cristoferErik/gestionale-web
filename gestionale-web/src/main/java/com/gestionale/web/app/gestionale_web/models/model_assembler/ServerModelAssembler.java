package com.gestionale.web.app.gestionale_web.models.model_assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gestionale.web.app.gestionale_web.controllers.ServerController;
import com.gestionale.web.app.gestionale_web.models.dto.ServerDto;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IServerDto;
@Component
public class ServerModelAssembler extends  RepresentationModelAssemblerSupport<IServerDto,ServerDto>{

    public ServerModelAssembler() {
        super(ServerController.class, ServerDto.class);
    }

    @Override
    public ServerDto toModel(IServerDto iServerDto) {
        ServerDto serverDto = new ServerDto();

        serverDto.setId(iServerDto.getId());
        serverDto.setServerUrl(iServerDto.getServerUrl());
        serverDto.setPanelloUrl(iServerDto.getPanelloUrl());
        serverDto.setUserServer(iServerDto.getUserServer());
        serverDto.setPasswordServer(iServerDto.getPasswordServer());
        serverDto.setCustomer(iServerDto.getCustomer().getId());
        return serverDto;
    }

    

}
