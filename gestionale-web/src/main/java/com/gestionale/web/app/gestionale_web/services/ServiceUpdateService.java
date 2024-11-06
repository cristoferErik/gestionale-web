package com.gestionale.web.app.gestionale_web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionale.web.app.gestionale_web.models.ServiceUpdate;

public interface ServiceUpdateService {
    public Page<ServiceUpdate> getAllServicesUpdate (Pageable pageable);
    public void update(Long id,ServiceUpdate serviceUpdate);
    public Optional<ServiceUpdate> getServiceUpdateById(Long id);
    public ServiceUpdate save(ServiceUpdate serviceUpdate);
    public Boolean delete(Long id);
    public List<ServiceUpdate> getAllServicesUpdateToWebSite(Long id);
}
