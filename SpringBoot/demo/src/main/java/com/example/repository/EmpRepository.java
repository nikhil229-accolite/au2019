package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.emp.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee, Integer>{

}
