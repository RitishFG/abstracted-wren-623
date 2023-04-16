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
	 Customer findByCustomerId (Integer customerId);

	 @Query("SELECT c FROM Customer c WHERE c.firstName = :name")
	 List<Customer> findByName(@Param ("name") String name);
	 Customer findByMobile(String mobile);
}
//	 Customer findByEmail (String email);
//	 Customer findByCustomerId (Integer customerId);
//    @Query("SELECT c FROM Customer c WHERE c.email = :email")
//    Customer findByEmail( String email);

