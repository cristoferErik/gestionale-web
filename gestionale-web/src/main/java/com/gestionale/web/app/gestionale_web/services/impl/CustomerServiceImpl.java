package com.gestionale.web.app.gestionale_web.services.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestionale.web.app.gestionale_web.exceptions.CustomException;
import com.gestionale.web.app.gestionale_web.models.Customer;
import com.gestionale.web.app.gestionale_web.repositories.CustomerRepository;
import com.gestionale.web.app.gestionale_web.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable){
        Page<Customer> customers = customerRepository.findAll(pageable);
        if(customers.isEmpty()){
            throw new CustomException("It is not possible list customers!",HttpStatus.NOT_FOUND);
        }
        return customers;
    }
    
    @Override
    public void updateCustomer(Customer customer,Long id) {
        try {
            if(getCustomerById(id).isPresent()){
                customerRepository.deleteById(id);
                save(customer);
            }
        } catch (Exception e) {
            throw new CustomException("It is not possible update customer with ID: !"+id,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer;
        } else {
            throw new CustomException("Customer with ID " + id + "was not found!",HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Boolean delete(Long id) {
        if(getCustomerById(id).isPresent()){
            customerRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
