package com.gestionale.web.app.gestionale_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestionale.web.app.gestionale_web.models.dto.ServerDto;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IServerDto;
import com.gestionale.web.app.gestionale_web.models.model_assembler.ServerModelAssembler;
import com.gestionale.web.app.gestionale_web.services.impl.ServerServiceImpl;

@CrossOrigin(originPatterns="*")
@RestController
@RequestMapping("/servers")
public class ServerController {
    @Autowired
    private ServerServiceImpl serverServiceImpl;
    
    @Autowired
    private ServerModelAssembler serverModelAssembler;
    
    @Autowired
    private PagedResourcesAssembler<IServerDto> pagedResourcesAssembler;

    @GetMapping("/{customerId}")
    public ResponseEntity<PagedModel<ServerDto>> getAll(
        @PathVariable Long customerId,
        @RequestParam(value= "page",defaultValue="0") Integer page,
        @RequestParam(value="size",defaultValue="1") Integer size
        ){
        Pageable pageable = PageRequest.of(page,size);
        Page<IServerDto> IserverPage = serverServiceImpl.getAllServersByCustomer(pageable,customerId);
        PagedModel<ServerDto> resources = pagedResourcesAssembler.toModel(IserverPage,serverModelAssembler);
        return ResponseEntity.ok(resources);

    }

}
