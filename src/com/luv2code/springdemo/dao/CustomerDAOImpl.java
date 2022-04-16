package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	//need to inject the section factory
	@Autowired
	private SessionFactory sessionFactory;                //It has to be the same name of the XML file "<bean id="sessionFactory"... </bean>"
	
	@Override                           
	public List<Customer> getCustomers(int sort) {
		
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		//get sort field
		
		String theSortField = null;
		
		switch (sort) {
		case SortUtils.FIRST_NAME:
			theSortField = "first_name";
			break;
		case SortUtils.LAST_NAME:
			theSortField = "last_name";
			break;
		case SortUtils.EMAIL:
			theSortField = "email";
			break;
		default:
			theSortField = "last_name";
		}
		
		//create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by " + theSortField, Customer.class);
		
		//execute query and get the result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session session = sessionFactory.getCurrentSession();
		
		//Hibernate will check, if the id is empty then it'll do an insert, otherwise hibernate will do an update.
		session.saveOrUpdate(customer);
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		
		Session session = sessionFactory.getCurrentSession();
		session.update(customer);
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		
		return sessionFactory.getCurrentSession().get(Customer.class, customerId);
	}

	@Override
	public void deleteCustomer(int customerId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = session.createQuery("delete from Customer where id = :theId");
		theQuery.setParameter("theId", customerId);
		
		theQuery.executeUpdate();
				
	}

	@Override
	public List<Customer> searchCustomer(String searchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> query = null;
		
		if (searchName != null && searchName.trim().length() > 0) {
			
			query = currentSession.createQuery("from Customer where lower(firstName) like :theName "
					+ "or lower(lastName) like :theName order by firstName", Customer.class);
			
			query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
			
		} else {
			
			query = currentSession.createQuery("from Customer order by firstName", Customer.class);
		}
		
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

	
}
