package com.customer.Service;

import java.time.LocalDateTime;

import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.Entity.Admin;
import com.customer.Entity.CurrentUserSession;
import com.customer.Entity.Customer;
import com.customer.Entity.Login;
import com.customer.Entity.Operator;
import com.customer.Repository.AdminRepository;
import com.customer.Repository.CurrentUserSessionRepository;
import com.customer.Repository.CustomerRepository;
import com.customer.Repository.OperatorRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	CustomerRepository customerDao;

	@Autowired
	OperatorRepository operatorDao;

	@Autowired
	CurrentUserSessionRepository sessionDao;

	@Autowired
	AdminRepository adminDao;

	@Override
	public String loginUser(Login credential) throws LoginException {
		
		

		CurrentUserSession currentUserSession;

		// checking for customer
	if (credential.getUser_Type().equals("CUSTOMER")) {

			Customer existCustomer = customerDao.findByEmail(credential.getEmail());
			
		
		
			if (existCustomer == null)
				throw new LoginException("Please Enter a valid CREDENTIALS");

			Optional<CurrentUserSession> validCustomerSession = sessionDao.findById(existCustomer.getCustomerId());

			if (validCustomerSession.isPresent()) {

				throw new LoginException("User allready Logged ");

			}

			if (existCustomer.getPassword().equals(credential.getPassword())) {

				String key = UUID.randomUUID().toString();
				
				currentUserSession = new CurrentUserSession(existCustomer.getCustomerId(), key, LocalDateTime.now(),
						credential.getUser_Type());

				System.out.println(credential.getUser_Type());
				
				sessionDao.save(currentUserSession);

				return key;
			} else
				throw new LoginException("Passowrd incorrect");
		}

		/// checking for Admin
		if (credential.getUser_Type().equals("ADMIN")) {

						
			Admin existAdmin = adminDao.findByEmail(credential.getEmail());
			if (existAdmin == null)
				throw new LoginException("Please Enter a valid CREDENTIALS");

			Optional<CurrentUserSession> validAdminSessionOpt = sessionDao.findById(existAdmin.getAdminId());

			if (validAdminSessionOpt.isPresent()) {

				throw new LoginException("User already Logged exist with this Email");

			}
			
			// Check Admin Password

			if (existAdmin.getPassword().equals(credential.getPassword())) {

				String key = UUID.randomUUID().toString();

				currentUserSession = new CurrentUserSession(existAdmin.getAdminId(), key, LocalDateTime.now(),
						credential.getUser_Type());

				sessionDao.save(currentUserSession);

				return key;
			} else
				throw new LoginException("Passowrd incorrect");
		}

		if (credential.getUser_Type().equals("OPERATOR")) {

			Operator existingOperator = operatorDao.findByEmail(credential.getEmail());
			if (existingOperator == null)
				throw new LoginException("Please Enter a valid CREDENTIALS");

			java.util.Optional<CurrentUserSession> validOperatorSessionOpt = sessionDao
					.findById(existingOperator.getOperatorId());

			if (validOperatorSessionOpt.isPresent()) {

				throw new LoginException("User already Logged In with this Email");

			}

			if (existingOperator.equals(credential.getPassword())) {

				String key = UUID.randomUUID().toString();

				currentUserSession = new CurrentUserSession(existingOperator.getOperatorId(), key, LocalDateTime.now(),
						credential.getUser_Type());

				sessionDao.save(currentUserSession);

				return key;
			} else
				throw new LoginException("Passowrd incorrect");
		} else
			throw new LoginException("Please Enter a valid User");

	}

	@Override
	public String logoutUser(String Key) throws LoginException {

		CurrentUserSession checkUser = sessionDao.findByUuid(Key);

		if (checkUser == null) {
			throw new LoginException("Not Record Match with this id");
		}

		sessionDao.delete(checkUser);
		return "User LogOut Sucessfully";
	}

}
