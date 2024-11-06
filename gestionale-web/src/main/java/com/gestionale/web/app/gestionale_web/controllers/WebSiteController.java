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
import com.gestionale.web.app.gestionale_web.models.WebSite;
import com.gestionale.web.app.gestionale_web.models.dto.ServerDto;
import com.gestionale.web.app.gestionale_web.models.dto.WebSiteDto;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IWebSiteDto;
import com.gestionale.web.app.gestionale_web.models.model_assembler.WebSiteModelAssembler;

import com.gestionale.web.app.gestionale_web.services.impl.WebSiteServiceImpl;

@CrossOrigin(originPatterns="*")
@RestController
@RequestMapping("/websites")
public class WebSiteController {

    @Autowired
    private WebSiteModelAssembler webSiteModelAssembler;
    @Autowired
    private WebSiteServiceImpl webSiteServiceImpl;
    @Autowired
    private PagedResourcesAssembler<IWebSiteDto> pagedResourcesAssembler;
    
    @GetMapping("/{serverId}")
    public ResponseEntity<PagedModel<WebSiteDto>> getAll(
        @PathVariable Long serverId,
        @RequestParam(value= "page",defaultValue="0") Integer page,
        @RequestParam(value="size",defaultValue="10") Integer size
        ){
        Pageable pageable = PageRequest.of(page,size);
        Page<IWebSiteDto> IwebSitePage = webSiteServiceImpl.getAllWebSiteByServer(pageable,serverId);
        PagedModel<WebSiteDto> resources = pagedResourcesAssembler.toModel(IwebSitePage,webSiteModelAssembler);
        return ResponseEntity.ok(resources);
    }
    @PostMapping
    public  ResponseEntity<WebSiteDto> create(@RequestBody WebSiteDto webSiteDto){
        WebSite webSite = new WebSite();
        webSite.setName(webSiteDto.getName());
        webSite.setUrl(webSiteDto.getUrl());
        webSite.setPrice(webSiteDto.getPrice());
        webSite.setDateCreation(webSiteDto.getDateCreation());
        Server server = new Server();
        server.setId(webSiteDto.getServerId());
        webSite.setServer(server);
        webSiteServiceImpl.save(webSite);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(webSiteDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id){
        
        webSiteServiceImpl.delete(id);
        Map<String,String> body = new HashMap<>();

        body.put("message","Delete was succesful!");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
     @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> update(@PathVariable Long id,@RequestBody(required=false) WebSiteDto webSiteDto){
        WebSite webSite= new WebSite();
        webSite.setId(id);
        webSite.setName(webSiteDto.getName());
        webSite.setUrl(webSiteDto.getUrl());
        webSite.setPrice(webSiteDto.getPrice());
        webSite.setDateCreation(webSiteDto.getDateCreation());
        Server server = new Server();
        server.setId(webSiteDto.getServerId());
        webSite.setServer(server);

        webSiteServiceImpl.update(id,webSite);
        Map<String,String> body = new HashMap<>();
        body.put("message","Update was succesful!");
        return ResponseEntity.status(HttpStatus.OK).body(body);  
    }
}
