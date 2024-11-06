package com.gestionale.web.app.gestionale_web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestionale.web.app.gestionale_web.models.ServiceUpdate;
import com.gestionale.web.app.gestionale_web.models.dto.ServiceUpdateDto;
import com.gestionale.web.app.gestionale_web.models.model_assembler.ServiceUpdateAssembler;
import com.gestionale.web.app.gestionale_web.services.impl.ServiceUpdateServiceImpl;

@CrossOrigin(originPatterns="*")
@RestController
@RequestMapping("/serviceUpdates")
public class ServiceUpdateController {

    @Autowired
    private ServiceUpdateAssembler serviceUpdateAssembler;

    @Autowired
    private ServiceUpdateServiceImpl serviceUpdateServiceImpl;

    @Autowired
    private PagedResourcesAssembler<ServiceUpdate> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<ServiceUpdateDto>> getAllServiceUpdates(
        @RequestParam(value= "page",defaultValue="0") Integer page,
        @RequestParam(value="size",defaultValue="10") Integer size
        )
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<ServiceUpdate> serviceUpdatePage = serviceUpdateServiceImpl.getAllServicesUpdate(pageable);
        PagedModel<ServiceUpdateDto> resources = pagedResourcesAssembler.toModel(serviceUpdatePage,serviceUpdateAssembler);
        return ResponseEntity.ok(resources);
    }
     @PostMapping
    public  ResponseEntity<ServiceUpdateDto> create(@RequestBody ServiceUpdateDto serviceUpdateDto)
    {
        ServiceUpdate serviceUpdate = new ServiceUpdate();
        serviceUpdate.setDescription(serviceUpdateDto.getDescription());
        serviceUpdate.setPeriodUpdate(serviceUpdateDto.getPeriodUpdate());
        serviceUpdate.setDateStart(serviceUpdateDto.getDateStart());
        serviceUpdate.setDateEnd(serviceUpdateDto.getDateEnd());
        serviceUpdateServiceImpl.save(serviceUpdate);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceUpdateDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id)
    {
        serviceUpdateServiceImpl.delete(id);
        Map<String,String> body = new HashMap<>();

        body.put("message","Delete was succesful!");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> update(@PathVariable Long id,@RequestBody(required=false) ServiceUpdateDto serviceUpdateDto)
    {
        ServiceUpdate serviceUpdate= new ServiceUpdate();
        serviceUpdate.setId(id);
        serviceUpdate.setDescription(serviceUpdateDto.getDescription());
        serviceUpdate.setPeriodUpdate(serviceUpdateDto.getPeriodUpdate());
        serviceUpdate.setDateStart(serviceUpdateDto.getDateStart());
        serviceUpdate.setDateEnd(serviceUpdateDto.getDateEnd());

        serviceUpdateServiceImpl.update(id,serviceUpdate);
        Map<String,String> body = new HashMap<>();
        body.put("message","Update was succesful!");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
