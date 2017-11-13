package br.com.prova.cliente.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
     * Lista todos os clientes
     * 
     * @param customerId
     * @return
     */
    @Query("SELECT cus FROM customer cus "
            + "where c.customerId = ?1 ")
    public List<Customer> findByCustomer(Integer customerId);

}