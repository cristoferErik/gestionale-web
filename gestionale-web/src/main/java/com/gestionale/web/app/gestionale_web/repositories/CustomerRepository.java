package com.gestionale.web.app.gestionale_web.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionale.web.app.gestionale_web.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
