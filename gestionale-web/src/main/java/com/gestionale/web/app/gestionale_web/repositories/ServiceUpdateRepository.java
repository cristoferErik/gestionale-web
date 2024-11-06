package com.gestionale.web.app.gestionale_web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionale.web.app.gestionale_web.models.ServiceUpdate;

@Repository
public interface ServiceUpdateRepository extends JpaRepository<ServiceUpdate,Long> {
    @Query("""
        SELECT su FROM ServiceUpdate su 
        LEFT JOIN SuWsDetail s ON su.id = s.serviceUpdate.id AND s.webSite.id = :webSiteId
        WHERE s.id IS NULL
        """)
    public List<ServiceUpdate> findAllServiceUpdateIsNotSuWsDetailByWebSite(Long webSiteId);
}
