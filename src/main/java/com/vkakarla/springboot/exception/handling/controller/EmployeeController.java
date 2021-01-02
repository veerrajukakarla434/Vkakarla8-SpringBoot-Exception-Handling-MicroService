package com.vkakarla.springboot.exception.handling.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vkakarla.springboot.exception.handling.entities.Employee;
import com.vkakarla.springboot.exception.handling.error.CustomException;
import com.vkakarla.springboot.exception.handling.service.EmployeeService;
import com.vkakarla.springboot.exception.handling.validationUtil.EmployeeRequestValidationUtil;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRequestValidationUtil validationUtil;
	
	@PostMapping(value = "/saveEmployee")
	public ResponseEntity<Object> saveAllEmployees(@Valid @RequestBody Employee employee) {

		employeeService.saveEmployee(employee);
		return new ResponseEntity<Object>("Successfully Saved", HttpStatus.OK);
	}

	
	@GetMapping(value = "/getEmployee/empId/{empId}")
	public ResponseEntity<Object> getEmployee(@PathVariable Long empId) throws CustomException {

		validationUtil.validateEmployeeId(empId);
		Employee empresponse = employeeService.getEmployeeById(empId);
		return new ResponseEntity<Object>(empresponse, HttpStatus.OK);
		
	}

}
