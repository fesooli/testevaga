package br.com.prova.cliente.api;

import java.util.List;

public interface CustomerOperations {
	
	Customer createCustomer(Customer customer);
	
	List<Customer> searchCustomers();
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(Long customerId);
	
	Customer searchCustomer(Customer customer);
}
