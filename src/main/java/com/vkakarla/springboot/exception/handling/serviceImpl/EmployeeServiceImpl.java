package com.vkakarla.springboot.exception.handling.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkakarla.springboot.exception.handling.entities.Employee;
import com.vkakarla.springboot.exception.handling.repo.EmployeeRepository;
import com.vkakarla.springboot.exception.handling.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {

		employeeRepository.save(employee);

	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
