package com.customer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.customer.Entity.Customer;
import com.customer.Entity.Issue;
import com.customer.Entity.Solution;
import com.customer.Entity.Status;
import com.customer.Exception.CustomerException;
import com.customer.Exception.IssueException;
import com.customer.Exception.LoginException;
import com.customer.Service.OperatorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/OperatorController")
public class OperatorController {
	
	@Autowired
	private OperatorService service;
	
	@PostMapping("/customer")
	public ResponseEntity<String> CreateCustomer(@RequestBody Customer customer) throws CustomerException{
		
		
		return new ResponseEntity<String>(service.AddCustomerIssue(customer), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/customer")
	public ResponseEntity<String> updateIssue(@RequestBody Issue issue) throws IssueException{
		
		return new ResponseEntity<String>(service.UpdateIssue(issue), HttpStatus.OK);
	}
	
	@PutMapping("/IssueId/status")
	public ResponseEntity<String> CloseCustomerIssue(@PathVariable int IssueId, @PathVariable Status status) throws IssueException{
		
		return new ResponseEntity<String>(service.closeCustomerIssue(IssueId, status), HttpStatus.OK);
			
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer() throws CustomerException{
		
		return new ResponseEntity<List<Customer>>(service.findAllCustomer(), HttpStatus.OK);
		
	}
	
	@GetMapping("/customer/{cusId}")
	public ResponseEntity<Customer> findByCustomerId(@PathVariable int cusId) throws CustomerException{
		
		return new ResponseEntity<Customer>(service.findByCustomerId(cusId), HttpStatus.OK);
	}
	
	@GetMapping("/customerName/{name}")
	public ResponseEntity<List<Customer>> findCustomerByName(@PathVariable String name) throws CustomerException{
		
		return new ResponseEntity<List<Customer>>(service.findCustomerByFirstName(name), HttpStatus.OK);
	}
	
	@GetMapping("/customerEmail/{email}")
	public ResponseEntity<Customer> findByEmail(@PathVariable ("email") String email) throws CustomerException{
		
		return new ResponseEntity<Customer>(service.findCustomerByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/customerMobile/{mobile}")
	public ResponseEntity<Customer> findByMobileCustomer(@PathVariable String mobile) throws CustomerException{
		
		return new ResponseEntity<Customer>(service.findCustomerByMobile(mobile), HttpStatus.OK);
	}
	@PostMapping("/solution/{key}")
	public ResponseEntity<Solution>provideSolution(@Valid @RequestBody Solution s,@PathVariable("key")String key) throws LoginException, IssueException
	{
		Solution sol=service.provideSolution(s, key);
		return new ResponseEntity<>(sol,HttpStatus.OK);
	}

}
