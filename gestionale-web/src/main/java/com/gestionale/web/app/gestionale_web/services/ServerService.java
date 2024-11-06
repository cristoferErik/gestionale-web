package com.gestionale.web.app.gestionale_web.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionale.web.app.gestionale_web.models.Server;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IServerDto;


public interface ServerService {
    
    public Page<IServerDto> getAllServersByCustomer(Pageable pageable,Long id);
    public void update(Long id,Server server);
    public Optional<Server> getServerById(Long id);
    public Server save(Server server);
    public Boolean delete(Long id);
}
