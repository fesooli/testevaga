package br.com.prova.customer.api;

import java.util.List;

public interface CustomerOperations {
	
	Customer createCustomer(Customer customer);
	
	List<Customer> searchCustomers();
	
	Customer updateCustomer(Customer customer);
	
	Customer findCustomerByEmail(String email);
	
	void deleteCustomer(Long customerId);
	
	Customer searchCustomer(Customer customer);
}
