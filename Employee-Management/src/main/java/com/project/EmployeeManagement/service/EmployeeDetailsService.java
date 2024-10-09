package com.project.EmployeeManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.EmployeeManagement.model.EmployeeDetails;

@Service
public interface EmployeeDetailsService {

	public String addEmployee(EmployeeDetails e);

	public String removeEmployee(int id);

	public List<EmployeeDetails> fetchEmployeeDetails();

	public String updateEmployee(EmployeeDetails e, int id);

}
