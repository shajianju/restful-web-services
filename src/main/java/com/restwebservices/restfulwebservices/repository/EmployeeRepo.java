package com.restwebservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restwebservices.restfulwebservices.models.Employee;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
