package com.project.EmployeeManagement.model;

import org.springframework.http.HttpStatus;

public class ApiResponse {

	private String message;
	private HttpStatus status;
	private Object data;

	public ApiResponse(String response, HttpStatus created, EmployeeDetails e) {
		this.message = response;
		this.status = created;
		this.data = e;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
