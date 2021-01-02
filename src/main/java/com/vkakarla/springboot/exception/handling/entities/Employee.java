package com.vkakarla.springboot.exception.handling.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="employee", schema = "new_schema123")
public class Employee implements Serializable {
    	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	
	@NotEmpty(message = "first_name  must not be empty")
	@Column(name="first_name")
	private String firstName;
	
	@NotEmpty(message = "last_name must not be empty")
	@Column(name="last_name")
	private String lastName;
	
	@NotEmpty(message = "department must not be empty")
	@Column(name="department")
	private String department;
	
	@NotEmpty(message = "email must not be empty")
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}