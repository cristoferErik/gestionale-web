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

import com.gestionale.web.app.gestionale_web.models.Customer;
import com.gestionale.web.app.gestionale_web.models.Server;
import com.gestionale.web.app.gestionale_web.models.dto.ServerDto;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IServerDto;
import com.gestionale.web.app.gestionale_web.models.model_assembler.ServerModelAssembler;
import com.gestionale.web.app.gestionale_web.services.ServerService;

@CrossOrigin(originPatterns="*")
@RestController
@RequestMapping("/servers")
public class ServerController {
    @Autowired
    private ServerService serverServiceImpl;
    
    @Autowired
    private ServerModelAssembler serverModelAssembler;

    @Autowired
    private PagedResourcesAssembler<IServerDto> pagedResourcesAssembler;

    @GetMapping("/{customerId}")
    public ResponseEntity<PagedModel<ServerDto>> getAll(
        @PathVariable Long customerId,
        @RequestParam(value= "page",defaultValue="0") Integer page,
        @RequestParam(value="size",defaultValue="10") Integer size
        ){
        Pageable pageable = PageRequest.of(page,size);
        Page<IServerDto> IserverPage = serverServiceImpl.getAllServersByCustomer(pageable,customerId);
        PagedModel<ServerDto> resources = pagedResourcesAssembler.toModel(IserverPage,serverModelAssembler);
        return ResponseEntity.ok(resources);
    }
    @PostMapping
    public  ResponseEntity<ServerDto> create(@RequestBody ServerDto serverDto){
        Server server = new Server();
        server.setServerUrl(serverDto.getServerUrl());
        server.setPanelloUrl(serverDto.getPanelloUrl());
        server.setUserServer(serverDto.getUserServer());
        server.setPasswordServer(serverDto.getPasswordServer());
        Customer customer = new Customer();
        customer.setId(serverDto.getCustomerId());
        server.setCustomer(customer);
        serverServiceImpl.save(server);
        return ResponseEntity.status(HttpStatus.CREATED).body(serverDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id){
        serverServiceImpl.delete(id);
        Map<String,String> body = new HashMap<>();

        body.put("message","Delete was succesful!");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> update(@PathVariable Long id,@RequestBody(required=false) ServerDto serverDto){
        Server server= new Server();
        server.setId(id);
        server.setPanelloUrl(serverDto.getPanelloUrl());
        server.setServerUrl(serverDto.getServerUrl());
        server.setUserServer(serverDto.getUserServer());
        server.setPasswordServer(serverDto.getPasswordServer());
        Customer customer = new Customer();
        customer.setId(serverDto.getCustomerId());
        server.setCustomer(customer);

        serverServiceImpl.update(id,server);
        Map<String,String> body = new HashMap<>();
        body.put("message","Update was succesful!");
        return ResponseEntity.status(HttpStatus.OK).body(body);

        
    }
}
