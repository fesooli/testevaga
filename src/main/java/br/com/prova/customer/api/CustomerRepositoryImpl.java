package br.com.prova.customer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class CustomerRepositoryImpl {}/*implements CustomerOperations{

	//@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> searchCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.delete(customerId);
	}

	@Override
	public Customer searchCustomer(Customer customer) {
		return customerRepository.findOne(customer.getCustomerId());
	}

}
*/