package com.vkakarla.springboot.exception.handling.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vkakarla.springboot.exception.handling.application.Vkakarla8SpringBootExceptionHandlingMicroServiceApplication;
import com.vkakarla.springboot.exception.handling.entities.Employee;
import com.vkakarla.springboot.exception.handling.error.CustomException;
import com.vkakarla.springboot.exception.handling.repo.EmployeeRepository;
import com.vkakarla.springboot.exception.handling.serviceImpl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Vkakarla8SpringBootExceptionHandlingMicroServiceApplication.class)
@TestPropertySource(locations = "classpath:application-mock.properties")
public class EmployeeControllerTest {

	@Autowired
	EmployeeController employeeController;
	
	@Autowired
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	@MockBean
	EmployeeRepository employeeRepository;
	
	@Autowired 
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_ValidEmpId_when_calling_getEmployee_then_return_employeeDetails() {
		
		try {
			Employee employeeResponse = null;
			String empString = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("testdata/employeeResponse.json"), "UTF-8");
			employeeResponse = objectMapper.readValue(empString, new TypeReference<Employee>() {});
			
			when(employeeRepository.getEmbloyeById((long) 101)).thenReturn(employeeResponse);
			ResponseEntity<Object> response = employeeController.getEmployee((long)101);
			
			assertNotNull(response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void given_InValidEmpId_when_calling_getEmployee_then_return_ErrorDetails() {
		
		try {
			 
			when(employeeRepository.getEmbloyeById((long) -101)).thenThrow(new RuntimeException());
			ResponseEntity<Object> rponse = employeeController.getEmployee((long)-101);
			
			
		} catch (CustomException ex) { 
			assertNotNull(ex.getMessage());
			assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
			assertEquals("Employee Id Should be positive number", ex.getMessage());
		}
		
	}
	
	@Test
	public void given_ValidEmpIdButNoRecord_when_calling_getEmployee_then_return_ErrorDetails() {
		
		try {
			when(employeeRepository.getEmbloyeById((long) 104)).thenReturn(null);
			ResponseEntity<Object> rponse = employeeController.getEmployee((long)104);
			
		} catch (CustomException ex) { 
			assertNotNull(ex.getMessage());
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
			assertEquals("Employee not found for given id 104", ex.getMessage());
		}
		
		
	}
	
}
