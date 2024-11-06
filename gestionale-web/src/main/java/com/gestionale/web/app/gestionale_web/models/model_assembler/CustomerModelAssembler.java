package com.gestionale.web.app.gestionale_web.models.model_assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gestionale.web.app.gestionale_web.controllers.CustomerController;
import com.gestionale.web.app.gestionale_web.models.Customer;
import com.gestionale.web.app.gestionale_web.models.dto.CustomerDto;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerDto>{

    public CustomerModelAssembler() {
        super(CustomerController.class, CustomerDto.class);
    }
    @Override
    public CustomerDto toModel(Customer customer) {
        
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setCity(customer.getCity());
        customerDto.setAddress(customer.getAddress());
        customerDto.setEmail(customer.getEmail());
        customerDto.setCellphone(customer.getCellphone());

        return customerDto;
    } 
}
