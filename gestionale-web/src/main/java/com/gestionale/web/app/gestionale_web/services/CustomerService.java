package com.gestionale.web.app.gestionale_web.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestionale.web.app.gestionale_web.models.Customer;

public interface CustomerService {
    public Page<Customer> getAllCustomers(Pageable pageable);
    public void updateCustomer(Customer customer,Long id);
    public Optional<Customer> getCustomerById(Long id);
    public Customer save(Customer customer);
    public Boolean delete(Long id);
}
