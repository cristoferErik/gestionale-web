package com.gestionale.web.app.gestionale_web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestionale.web.app.gestionale_web.models.Customer;
import com.gestionale.web.app.gestionale_web.models.dto.CustomerDto;
import com.gestionale.web.app.gestionale_web.models.model_assembler.CustomerModelAssembler;
import com.gestionale.web.app.gestionale_web.services.impl.CustomerServiceImpl;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private CustomerModelAssembler customerModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Customer> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<CustomerDto>> getAll(
        @RequestParam (value= "page",defaultValue="0") Integer page,
        @RequestParam (value="size",defaultValue="1") Integer size
        )
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<Customer> customers = customerServiceImpl.getAllCustomers(pageable);
        PagedModel<CustomerDto> resources = pagedResourcesAssembler.toModel(customers,customerModelAssembler);
        
        return ResponseEntity.ok(resources);
    }
    @PostMapping
    public  ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setCity(customerDto.getCity());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setCellphone(customerDto.getCellphone());

        Customer savedCustomer=customerServiceImpl.save(customer);

        CustomerDto savedCustomerDto = new CustomerDto();
        savedCustomerDto.setId(savedCustomer.getId());
        savedCustomerDto.setName(savedCustomer.getName());
        savedCustomerDto.setLastName(savedCustomer.getLastName());
        savedCustomerDto.setCity(savedCustomer.getCity());
        savedCustomerDto.setAddress(savedCustomer.getAddress());
        savedCustomerDto.setEmail(savedCustomer.getEmail());
        savedCustomerDto.setCellphone(savedCustomer.getCellphone());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody(required=false) CustomerDto customerDto) {
        
        if(customerDto==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer data is missing or incomplete");
        }
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setCity(customerDto.getCity());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setCellphone(customerDto.getCellphone());

        customerServiceImpl.updateCustomer(customer,id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Updated was succesful!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        customerServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.FOUND).body("Delete was succesful!");
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id){
        Customer customer=customerServiceImpl.getCustomerById(id).get();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setCity(customer.getCity());
        customerDto.setAddress(customer.getAddress());
        customerDto.setEmail(customer.getEmail());
        customerDto.setCellphone(customer.getCellphone());
        return ResponseEntity.ok(customerDto);

    }
}
