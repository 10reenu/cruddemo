package com.coding.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.springboot.cruddemo.entity.Employee;
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field for entity manager
	
	private	EntityManager entitymanager;  
	
	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		
		entitymanager=theEntityManager;
	}
    @Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		
		 // get the current hibernate session
		Session currentSession= entitymanager.unwrap(Session.class);
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee",Employee.class);
		//execute query and get result list
		List<Employee> employees =theQuery.getResultList();
		//return the results
		return employees;
	}
	@Override
	public Employee findById(int theId) {
		// get the current hibernate session
		Session currentSession= entitymanager.unwrap(Session.class);
		//get the employees
		Employee theEmployee = currentSession.get(Employee.class, theId);
		//return the employee
		
		return theEmployee;
	}
	@Override
	public void save(Employee theEmployee) {
		// get the current hibernate session
				Session currentSession= entitymanager.unwrap(Session.class);
				
		// save employee
				currentSession.saveOrUpdate(theEmployee);
		
	}
	@Override
	public void deleteById(int theId) {
	
		// get the current hibernate session
		Session currentSession= entitymanager.unwrap(Session.class);
		
		// delete employee
		Query theQuery=currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId",theId);
		theQuery.executeUpdate();
	}

}
