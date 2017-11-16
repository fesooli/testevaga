package br.com.prova.customer.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
public interface CustomerRepository {}/*extends JpaRepository<Customer, Long> {


    @Query("SELECT cus FROM customer cus "
            + "where c.customerId = ?1 ")
    public List<Customer> findByCustomer(Integer customerId);

}*/