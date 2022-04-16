//We delegate all @Transactional annotation to Service.

package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	//need to inject the customer DAO. (Spring will scan the class which implements CustomerDAO interface)
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(int sort) {
		return customerDAO.getCustomers(sort);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		
		customerDAO.updateCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {	 
		
		return customerDAO.getCustomer(customerId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		
		customerDAO.deleteCustomer(customerId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomer(String searchName) {
		
		return customerDAO.searchCustomer(searchName);
	}

	
}
