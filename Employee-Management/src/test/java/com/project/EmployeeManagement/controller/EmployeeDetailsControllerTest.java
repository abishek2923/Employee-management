package com.project.EmployeeManagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.EmployeeManagement.model.ApiResponse;
import com.project.EmployeeManagement.model.EmployeeDetails;
import com.project.EmployeeManagement.service.EmployeeDetailsService;

class EmployeeDetailsControllerTest {

	@InjectMocks
	private EmployeeDetailsController controller;

	@Mock
	private EmployeeDetailsService service;

	private EmployeeDetails employee;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		employee = new EmployeeDetails();
		employee.setName("John Doe");
		employee.setEmail("john.doe@example.com");
		employee.setSalary(50000);
	}

	@Test
	    public void testAddEmployeeDetails() {
	        when(service.addEmployee(any(EmployeeDetails.class))).thenReturn("Employee details added successfully.");
	        ResponseEntity<ApiResponse> response = controller.addEmployeeDetails(employee);
	        ApiResponse apiResponse = response.getBody();
	        assert apiResponse != null;
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals("Employee details added successfully.", apiResponse.getMessage());
	        assertEquals(employee, apiResponse.getData());
	        verify(service, times(1)).addEmployee(employee);
	    }

	@Test
	    public void testRemoveEmployeeDetails() {
	        when(service.removeEmployee(1)).thenReturn("Employee with id: 1 removed successfully.");
	        ResponseEntity<ApiResponse> response = controller.removeEmployeeDetails(1);
	        ApiResponse apiResponse = response.getBody();
	        assert apiResponse != null;
	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertEquals("Employee with id: 1 removed successfully.", apiResponse.getMessage());
	        assertNull(apiResponse.getData());
	        verify(service, times(1)).removeEmployee(1);
	    }

	@Test
	public void testFetchEmployeeDetails() {
		List<EmployeeDetails> employees = Arrays.asList(employee);
		when(service.fetchEmployeeDetails()).thenReturn(employees);
		ResponseEntity<List<EmployeeDetails>> response = controller.fetchEmployeeDetails();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employees, response.getBody());
		verify(service, times(1)).fetchEmployeeDetails();
	}

	@Test
	    public void testUpdateEmployee() {
	        when(service.updateEmployee(any(EmployeeDetails.class), eq(1)))
	                .thenReturn("Employee details with id: 1 updated successfully.");
	        ResponseEntity<ApiResponse> response = controller.updateEmployee(employee, 1);
	        ApiResponse apiResponse = response.getBody();
	        assert apiResponse != null;
	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertEquals("Employee details with id: 1 updated successfully.", apiResponse.getMessage());
	        assertEquals(employee, apiResponse.getData());
	        verify(service, times(1)).updateEmployee(employee, 1);
	    }
}
