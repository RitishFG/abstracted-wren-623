package com.customer.Service;

import java.util.List;

import com.customer.Entity.Customer;
import com.customer.Entity.Issue;
import com.customer.Entity.Solution;
import com.customer.Entity.Status;
import com.customer.Exception.CustomerException;
import com.customer.Exception.IssueException;
import com.customer.Exception.LoginException;

public interface OperatorService {
	
	public String AddCustomerIssue(Customer customer) throws CustomerException;
	
	public String UpdateIssue(Issue issue) throws IssueException;
	
	public String closeCustomerIssue(int IssueId, Status status) throws IssueException;
	
	public List<Customer> findAllCustomer() throws CustomerException;
	
	public Customer findByCustomerId(int cusId) throws CustomerException;
	
	public List<Customer> findCustomerByFirstName(String name) throws CustomerException;
	
	public Customer findCustomerByEmail(String email) throws CustomerException;
	
	public Customer findCustomerByMobile(String mobile) throws CustomerException;

	public Solution provideSolution(Solution s,String key) throws LoginException, IssueException;
	
	
	

}
