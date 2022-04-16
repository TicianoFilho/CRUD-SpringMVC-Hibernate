package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerDAO {

	
	public List<Customer> getCustomers(int sort);

	public void saveCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomer(String searchName);
}
