package com.vkakarla.springboot.exception.handling.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	 @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	            details.add(error.getDefaultMessage());
	        }
	        ErrorResponse error = new ErrorResponse();
	        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
	        error.setMessage("Validation Failed");
	        error.setDetails(details);
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	
	@ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandle(CustomException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
        ErrorResponse errors = new ErrorResponse();
        errors.setStatusCode(ex.getStatus().value());
        errors.setMessage(ex.getStatus().name());
        details.add(ex.getMessage());
        errors.setDetails(details);
        return new ResponseEntity<>(errors, ex.getStatus());

    }

	
}
