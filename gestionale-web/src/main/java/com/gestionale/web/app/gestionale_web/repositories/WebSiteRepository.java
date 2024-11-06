package com.gestionale.web.app.gestionale_web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionale.web.app.gestionale_web.models.WebSite;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IWebSiteDto;

@Repository
public interface WebSiteRepository extends JpaRepository<WebSite,Long>{
    Page<IWebSiteDto> findByServerId(Long serverId,Pageable pageable);   
}
