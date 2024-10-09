package com.project.EmployeeManagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.EmployeeManagement.DAO.EmployeeDetailsRepository;
import com.project.EmployeeManagement.Exception.CustomException;
import com.project.EmployeeManagement.model.EmployeeDetails;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	@Autowired
	private EmployeeDetailsRepository dao;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDetailsServiceImpl.class);

	@Override
	@Transactional
	public String addEmployee(EmployeeDetails e) {
		logger.info("Processing query for adding a new employee: {}", e.getName());
		validateEmployeeDetails(e);
		dao.save(e);
		return "Employee details added successfully.";
	}

	@Override
	@Transactional
	public String removeEmployee(int id) {
		logger.info("Processing query for removing the employee");
		EmployeeDetails employee = dao.findById(id)
				.orElseThrow(() -> new CustomException("Employee not found with id: " + id));
		dao.delete(employee);
		return "Employee with id: " + id + " removed successfully.";
	}

	@Cacheable("employees")
	@Override
	public List<EmployeeDetails> fetchEmployeeDetails() {
		logger.info("Processing query for fetching all employees");
		return dao.findAll();
	}

	@Override
	@Transactional
	public String updateEmployee(EmployeeDetails e, int id) {
		logger.info("Processing query for updating details for the employee: {}", e.getName());
		EmployeeDetails employee = dao.findById(id)
				.orElseThrow(() -> new CustomException("Employee not found with id: " + id));
		validateEmployeeDetails(e);
		employee.setName(e.getName());
		employee.setEmail(e.getEmail());
		employee.setSalary(e.getSalary());
		dao.save(employee);
		return "Employee details with id: " + id + " updated successfully.";
	}

	private void validateEmployeeDetails(EmployeeDetails e) {
		if (e.getName() == null || e.getName().isEmpty()) {
			throw new CustomException("Employee name is empty");
		}
		if (e.getEmail() == null || e.getEmail().isEmpty()) {
			throw new CustomException("Employee email is empty");
		}
		if (e.getSalary() <= 0) {
			throw new CustomException("Employee salary is invalid");
		}
	}

}
