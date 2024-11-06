package com.gestionale.web.app.gestionale_web.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionale.web.app.gestionale_web.models.Software;
import com.gestionale.web.app.gestionale_web.services.SoftwareService;

public class SoftwareServiceImpl implements SoftwareService{

    @Override
    public Page<Software> getAllSoftware(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateSoftware(Software software, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Software> getCustomerById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Software save(Software software) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean deleteSoftware(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
