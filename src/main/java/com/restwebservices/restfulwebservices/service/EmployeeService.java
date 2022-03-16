package com.restwebservices.restfulwebservices.service;

import java.util.List;

import com.restwebservices.restfulwebservices.exception.EmployCustomException;
import com.restwebservices.restfulwebservices.exception.UserNotFoundException;
import com.restwebservices.restfulwebservices.models.Employee;

public interface EmployeeService  {

	public void addUser(Employee employee) throws EmployCustomException;
	public void editUser(Employee employee) throws UserNotFoundException;
	public void deleteUser(long id) throws UserNotFoundException;
	public List<Employee> listAll() throws EmployCustomException;
	public Employee getUser(long id) throws UserNotFoundException;



}
