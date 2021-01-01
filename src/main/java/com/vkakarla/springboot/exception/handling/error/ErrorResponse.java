package com.vkakarla.springboot.exception.handling.error;

import java.util.List;

public class ErrorResponse {

	private int statusCode;
    private String message;
    private List<String> details;
    
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	
}
