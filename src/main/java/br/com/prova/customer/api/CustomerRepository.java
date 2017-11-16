package br.com.prova.customer.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("select c from Customer c "
            + "where c.email = :#{#email} ")
	Customer findCustomerByEmail(@Param("email") String email);
	
	@Query("select c from Customer c " +
			"inner join c.club cl " +
			"inner join cl.campaigns cam " +
            "where c.email = :#{#email} ")
	Customer findCustomerCampaigns(@Param("email") String email);

}