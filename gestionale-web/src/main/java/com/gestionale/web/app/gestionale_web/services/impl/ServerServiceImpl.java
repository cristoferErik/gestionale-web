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
    public void update(Long id,Server server) {
        try {
            if(getServerById(id).isPresent()){
                save(server);
            }
        } catch (Exception e) {
            throw new CustomException("Non è possibile aggiornare il server con il ID: "+id,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<Server> getServerById(Long id) {
        Optional<Server> server = serverRepository.findById(id);
        if (server.isPresent()) {
            return server;
        } else {
            throw new CustomException("Server with ID " + id + "was not found!",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Server save(Server server) {
        // TODO Auto-generated method stub
       return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        if(getServerById(id).isPresent()){
            serverRepository.deleteById(id);
            return true;
        }else{
            return false;
        }  
    }

}
