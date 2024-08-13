package com.gestionale.web.app.gestionale_web.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestionale.web.app.gestionale_web.exceptions.CustomException;
import com.gestionale.web.app.gestionale_web.models.Customer;
import com.gestionale.web.app.gestionale_web.models.Server;
import com.gestionale.web.app.gestionale_web.models.dtoInterface.IServerDto;
import com.gestionale.web.app.gestionale_web.repositories.ServerRepository;
import com.gestionale.web.app.gestionale_web.services.CustomerService;
import com.gestionale.web.app.gestionale_web.services.ServerService;

@Service
public class ServerServiceImpl implements ServerService{

    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private CustomerService customerService;

    @Override
    public Page<IServerDto> getAllServersByCustomer(Pageable pageable, Long id) {
        //qui ce a exception per cio non bisogna aggiungere niente piu
        Customer customer=customerService.getCustomerById(id).get();
        Page<IServerDto> servers = serverRepository.findByCustomerId(customer.getId(),pageable);
        if (servers.isEmpty()) {
            throw new CustomException("Server that belongs to customerId: " + customer.getId() + "was not found!",HttpStatus.NOT_FOUND);
        }else{
            return servers;
        }
    }

    @Override
    public Optional<Server> update(Server server, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<Server> getServerById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getServerById'");
    }

    @Override
    public Server save(Server server) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
