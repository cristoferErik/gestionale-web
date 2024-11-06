package com.gestionale.web.app.gestionale_web.models.model_assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gestionale.web.app.gestionale_web.controllers.ServiceUpdateController;
import com.gestionale.web.app.gestionale_web.models.ServiceUpdate;
import com.gestionale.web.app.gestionale_web.models.dto.ServiceUpdateDto;

@Component
public class ServiceUpdateAssembler extends  RepresentationModelAssemblerSupport<ServiceUpdate, ServiceUpdateDto> {

    public ServiceUpdateAssembler() {
        super(ServiceUpdateController.class, ServiceUpdateDto.class);
    }

    @Override
    public ServiceUpdateDto toModel(ServiceUpdate serviceUpdate) {
        ServiceUpdateDto serviceUpdateDto = new ServiceUpdateDto();
        serviceUpdateDto.setId(serviceUpdate.getId());
        serviceUpdateDto.setDescription(serviceUpdate.getDescription());
        serviceUpdateDto.setPeriodUpdate(serviceUpdate.getPeriodUpdate());
        serviceUpdateDto.setDateStart(serviceUpdate.getDateStart());
        serviceUpdateDto.setDateEnd(serviceUpdate.getDateEnd());
        
        return serviceUpdateDto;
    }

}
