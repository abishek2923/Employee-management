package com.project.EmployeeManagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.EmployeeManagement.model.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ApiResponse response = new ApiResponse("An error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR, null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(CustomException ex, WebRequest request) {
		ApiResponse response = new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND, null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
