package com.gestionale.web.app.gestionale_web.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionale.web.app.gestionale_web.models.Software;

public interface SoftwareService {
    public Page<Software> getAllSoftware(Pageable pageable);
    public void updateSoftware(Software software,Long id);
    public Optional<Software> getCustomerById(Long id);
    public Software save(Software software);
    public Boolean deleteSoftware(Long id);
}
