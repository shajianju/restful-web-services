package com.restwebservices.restfulwebservices.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.restwebservices.restfulwebservices.exception.EmployCustomException;
import com.restwebservices.restfulwebservices.exception.UserNotFoundException;
import com.restwebservices.restfulwebservices.models.Employee;
import com.restwebservices.restfulwebservices.repository.EmployeeRepo;
import com.restwebservices.restfulwebservices.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public void addUser(Employee employee) throws EmployCustomException {
		employeeRepo.save(employee);
	}

	@Override
	public void editUser(Employee employee) throws UserNotFoundException {
		if(!employeeRepo.existsById(employee.getId()))	
			throw new UserNotFoundException();
		employeeRepo.save(employee);
	}

	@Override
	public void deleteUser(long id) throws UserNotFoundException {
		employeeRepo.deleteById(id);
	}

	@Override
	public List<Employee> listAll()  throws EmployCustomException{
		return employeeRepo.findAll();
	}

	@Override
	public Employee getUser(long id) throws UserNotFoundException {
		return employeeRepo.getById(id);
	}

}
