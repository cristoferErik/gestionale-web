package com.gestionale.web.app.gestionale_web.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestionale.web.app.gestionale_web.exceptions.CustomException;
import com.gestionale.web.app.gestionale_web.models.ServiceUpdate;
import com.gestionale.web.app.gestionale_web.repositories.ServiceUpdateRepository;
import com.gestionale.web.app.gestionale_web.repositories.SuWsDetailRepository;
import com.gestionale.web.app.gestionale_web.services.ServiceUpdateService;

@Service
public class ServiceUpdateServiceImpl implements ServiceUpdateService{

    @Autowired
    private ServiceUpdateRepository serviceUpdateRepository;
    @Autowired
    private SuWsDetailRepository suWsDetailRepository;

    @Override
    public Page<ServiceUpdate> getAllServicesUpdate(Pageable pageable) {
        Page<ServiceUpdate> serviceUpdate = serviceUpdateRepository.findAll(pageable);
        if(serviceUpdate.isEmpty()){
            throw new CustomException("Lista dei servizi di aggiornamenti è vuoto!",HttpStatus.OK);
        }
        return serviceUpdate;
    }

    @Override
    public void update(Long id, ServiceUpdate serviceUpdate) {
        if(getServiceUpdateById(id).isPresent()){
            save(serviceUpdate);
        }
    }

    @Override
    public Optional<ServiceUpdate> getServiceUpdateById(Long id) {
        Optional<ServiceUpdate> serviceUpdate = serviceUpdateRepository.findById(id);
        if (serviceUpdate.isPresent()) {
            return serviceUpdate;
        } else {
            throw new CustomException("ServiceUpdate with ID " + id + "was not found!",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ServiceUpdate save(ServiceUpdate serviceUpdate) {
        return serviceUpdateRepository.save(serviceUpdate);
    }

    @Override
    public Boolean delete(Long id) {
        if(getServiceUpdateById(id).isPresent()){
            serviceUpdateRepository.deleteById(id);
            return true;
        }else{
            return false;
        }  
    }
    //Continuare qui!!!
    @Override
    public List<ServiceUpdate> getAllServicesUpdateToWebSite(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    

}
