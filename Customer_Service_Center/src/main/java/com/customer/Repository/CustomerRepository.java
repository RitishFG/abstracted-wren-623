package com.customer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	 Customer findByEmail (String email);
	 
	 @Query("SELECT c FROM Customer c WHERE c.customerId= :id")
	 Customer findByCustomerId (@Param ("id") int customerId);

	 @Query("SELECT c FROM Customer c WHERE c.firstName = :name")
	 List<Customer> findByName(@Param ("name") String name);
	 
	 Customer findByMobile(String mobile);
}
