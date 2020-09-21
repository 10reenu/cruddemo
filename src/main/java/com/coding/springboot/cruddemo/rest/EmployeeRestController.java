package com.coding.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.springboot.cruddemo.dao.EmployeeDAO;
import com.coding.springboot.cruddemo.entity.Employee;
import com.coding.springboot.cruddemo.service.EmployeeService;

@RequestMapping("/api")
@RestController
public class EmployeeRestController {
     private EmployeeService employeeService;
	//quick and dirty :inject employee dao
	@Autowired
	public EmployeeRestController(EmployeeService theemployeeService) {
		employeeService = theemployeeService;
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
		
	}
		//add mapping for GET /employees/{employeeid}
		
		@GetMapping("/employees/{employeeId}")
		public Employee getEmployee( @PathVariable int employeeId) {
			
			Employee theEmployee =employeeService.findById(employeeId);
			
			if(theEmployee==null) {
				throw new RuntimeException("Employee id not found "+employeeId);
			}
			return theEmployee;
	
		}
		
		@PostMapping("/employees")
		public Employee addEmployee(@RequestBody Employee theEmployee) {
			//also just in case they pass an id in JSOn .....set id to 0
			//this is to force a save of new item.........insteadof update
			theEmployee.setId(0);
			employeeService.save(theEmployee);
			return theEmployee;
		}
		
		// add mapping for put/employees - updat the existing
		
		@PutMapping("/employees")
		public Employee updateEmployee(@RequestBody Employee theEmployee ) {
			
			employeeService.save(theEmployee);
			return theEmployee;	
		}
		
		// add mapping for delete/emplyees/{emplpoyeeId}- deleteEmplyee
	    	@DeleteMapping("/employees/{employeeId}")
		public Employee deleteEmployee(@PathVariable int employeeId) {
			
			Employee tempEmployee =employeeService.findById(employeeId);
			// thow exception if null
			if(tempEmployee==null) {
				throw new RuntimeException("Employee id not found "+ employeeId);
			}
			employeeService.deleteById(employeeId);
			return tempEmployee;
			
		}
		
		
	}

    
