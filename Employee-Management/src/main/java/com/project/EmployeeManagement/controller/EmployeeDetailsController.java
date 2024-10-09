package com.project.EmployeeManagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeManagement.model.ApiResponse;
import com.project.EmployeeManagement.model.EmployeeDetails;
import com.project.EmployeeManagement.service.EmployeeDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeDetailsController {

	@Autowired
	private EmployeeDetailsService service;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDetailsController.class);

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addEmployeeDetails(@RequestBody EmployeeDetails e) {
		logger.info("Adding a new employee: {}", e.getName());
		String response = service.addEmployee(e);
		ApiResponse apiResponse = new ApiResponse(response, HttpStatus.CREATED, e);
		logger.info("Successfully added new employee.");
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/remove")
	public ResponseEntity<ApiResponse> removeEmployeeDetails(@RequestParam("id") int id) {
		logger.info("Removing an existing employee with id: {}", id);
		String response = service.removeEmployee(id);
		ApiResponse apiResponse = new ApiResponse(response, HttpStatus.CREATED, null);
		logger.info("Successfully removed employee with id: {}", id);
		return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
	}

	@GetMapping("/details")
	public ResponseEntity<List<EmployeeDetails>> fetchEmployeeDetails() {
		logger.info("Fetching all employees details");
		List<EmployeeDetails> employees = service.fetchEmployeeDetails();
		logger.info("Successfully fetched all employees details");
		return ResponseEntity.ok(employees);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateEmployee(@RequestBody EmployeeDetails e, @PathVariable int id) {
		logger.info("Updating an exising employee details with id: {}", id);
		String response = service.updateEmployee(e, id);
		ApiResponse apiResponse = new ApiResponse(response, HttpStatus.CREATED, e);
		logger.info("Successfully updated exising employee details with id: {}", id);
		return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
	}

}
