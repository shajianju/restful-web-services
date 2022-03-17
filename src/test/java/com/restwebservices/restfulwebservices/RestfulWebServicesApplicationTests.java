package com.restwebservices.restfulwebservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.restwebservices.restfulwebservices.models.Account;
import com.restwebservices.restfulwebservices.models.Employee;
import com.restwebservices.restfulwebservices.repository.AccountRepo;
import com.restwebservices.restfulwebservices.repository.EmployeeRepo;


@SpringBootTest
@RunWith(SpringRunner.class)
class RestfulWebServicesApplicationTests {
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;


	@Test
	public void testCreate() {
		
		Account acc=new Account();
		acc.setName("ABC");
		acc.setEmpList(new ArrayList<>());
		accountRepo.save(acc);
		assertNotNull(accountRepo.findAll());
		
	}
	
	@Test
	public void testAddNewUser() {
		
		Employee employee=new Employee("AKHIL", "31/12/1995");
		
		employeeRepo.save(employee);
		
		assertNotNull(employeeRepo.findAll());
		
	}
	
	
	@Test
	public void testListAll() {
		assertThat(accountRepo.findAll()).size().isGreaterThan(0);
		
	}
}
