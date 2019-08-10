package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp.Employee;
import com.example.repository.EmpRepository;

import CustomException.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmpRepository empRepository;

	@GetMapping("/Employees")
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}

	@PostMapping("/Employees")
	public Employee createEmployee(@RequestBody Employee e) {
		return empRepository.save(e);

	}
	
	@GetMapping("/Employees/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Integer id) {
	    return empRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
	}
	
	
	
/*	// Update a Employee
	@PutMapping("/Employees/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") Long EmployeeId,
	                                        @Valid @RequestBody Employee e) {

	    Employee Employee = empRepository.findById(EmployeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", EmployeeId));

	    Employee.setTitle(EmployeeDetails.getTitle());
	    Employee.setContent(EmployeeDetails.getContent());

	    Employee updatedEmployee = EmployeeRepository.save(Employee);
	    return updatedEmployee;
	}*/

}
