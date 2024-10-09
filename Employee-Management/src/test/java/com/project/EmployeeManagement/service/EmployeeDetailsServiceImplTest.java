package com.project.EmployeeManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.EmployeeManagement.DAO.EmployeeDetailsRepository;
import com.project.EmployeeManagement.Exception.CustomException;
import com.project.EmployeeManagement.model.EmployeeDetails;

class EmployeeDetailsServiceImplTest {

	@Mock
	private EmployeeDetailsRepository dao;

	@InjectMocks
	private EmployeeDetailsServiceImpl service;

	private EmployeeDetails employee;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		employee = new EmployeeDetails();
		employee.setEmployee_id(1);
		employee.setName("John Doe");
		employee.setEmail("john.doe@example.com");
		employee.setSalary(55000);
	}

	@Test
    public void testAddEmployee() {
        when(dao.save(any(EmployeeDetails.class))).thenReturn(employee);
        String response = service.addEmployee(employee);
        assertEquals("Employee details added successfully.", response);
        verify(dao, times(1)).save(employee);
    }

	@Test
    public void testRemoveEmployee() {
        when(dao.findById(1)).thenReturn(Optional.of(employee));
        String response = service.removeEmployee(1);
        assertEquals("Employee with id: 1 removed successfully.", response);
        verify(dao, times(1)).delete(employee);
    }

	@Test
    public void testRemoveEmployeeNotFound() {
        // Arrange
        when(dao.findById(1)).thenReturn(Optional.empty());
        CustomException thrown = assertThrows(CustomException.class, () -> {
            service.removeEmployee(1);
        });
        assertEquals("Employee not found with id: 1", thrown.getMessage());
    }

	@Test
    public void testUpdateEmployee() {
        when(dao.findById(1)).thenReturn(Optional.of(employee));
        EmployeeDetails updatedEmployee = new EmployeeDetails();
        updatedEmployee.setName("Jane Doe");
        updatedEmployee.setEmail("jane.doe@example.com");
        updatedEmployee.setSalary(60000);
        String response = service.updateEmployee(updatedEmployee, 1);
        assertEquals("Employee details with id: 1 updated successfully.", response);
        verify(dao, times(1)).save(employee);
    }

}
