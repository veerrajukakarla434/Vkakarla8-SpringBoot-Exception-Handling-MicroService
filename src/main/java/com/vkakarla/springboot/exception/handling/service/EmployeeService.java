package com.vkakarla.springboot.exception.handling.service;

import com.vkakarla.springboot.exception.handling.entities.Employee;

public interface EmployeeService {
	
	
	public void saveEmployee(Employee employee);
	
	public Employee getEmployeeById(long id);
	
	

}
