package com.gestionale.web.app.gestionale_web.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionale.web.app.gestionale_web.models.Server;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IServerDto;

//Se indica Il projection IServerDto grazie al Server
public interface ServerRepository extends JpaRepository<Server, Long>{
    Page<IServerDto> findByCustomerId(Long customerId,Pageable pageable);
}
