package com.restwebservices.restfulwebservices.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restwebservices.restfulwebservices.beans.ResponseData;
import com.restwebservices.restfulwebservices.exception.AccountNotFoundException;
import com.restwebservices.restfulwebservices.exception.EmployCustomException;
import com.restwebservices.restfulwebservices.exception.ExceptionBody;
import com.restwebservices.restfulwebservices.jwt.AuthenticationRequest;
import com.restwebservices.restfulwebservices.jwt.AuthenticationResponse;
import com.restwebservices.restfulwebservices.jwt.JwtUtil;
import com.restwebservices.restfulwebservices.models.Account;
import com.restwebservices.restfulwebservices.models.Employee;
import com.restwebservices.restfulwebservices.securiy.MyUserDetailService;
import com.restwebservices.restfulwebservices.serviceImpl.AccountServiceImpl;
import com.restwebservices.restfulwebservices.serviceImpl.EmployeeServiceImpl;

import io.jsonwebtoken.Jwts;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeServiceImpl;

	@Autowired
	AccountServiceImpl accountServiceImpl;

	@Autowired
	Environment environment;


	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService userDetailService;

	@Autowired
	private JwtUtil jwtutil;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest req){

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

		final UserDetails details=userDetailService.loadUserByUsername(req.getUsername());
		final String twt=jwtutil.generateToken(details);
		return ResponseEntity.ok(new AuthenticationResponse(twt));
	}


	public EntityModel<ResponseData> addNewUser(@Valid @RequestBody Employee bean){

		ResponseData responseData=new ResponseData();
		EntityModel<ResponseData> responseModel=EntityModel.of(responseData);

		try {

			/*
			 * final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
			 * final LocalDate dt = dtf.parseLocalDate(bean.getBirthDate()); DateFormatter
			 * format=DateFormatter.ofPattern("YYYY-mm-dd"); LocalDate
			 * date=LocalDate.parse(bean.getBirthDate(),format);
			 */

			/*
			 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			 * LocalDate date = LocalDate.parse(bean.getBirthdate(), formatter); Employee
			 * emp=new Employee(bean.getName(), date);
			 */
			employeeServiceImpl.addUser(bean);

			/*
			 * WebMvcLinkBuilder
			 * linkToUser=linkTo(methodOn(this.getClass().descriptorString()));
			 * responseModel.add(linkToUser.withRel("all-users"));
			 */

			responseData.setException(new ExceptionBody("SUCCESS", new Date(), "List of employees", HttpStatus.CREATED));



		}
		catch(EmployCustomException e) {

			responseData.setException(new ExceptionBody("ERROR", new Date(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));

		}

		/*
		 * WebMvcLinkBuilder builder=linkTo(methodOn(this.getClass(), getAllEmp()));
		 * responseModel.add(builder.withRel("list-all-user"));
		 */

		return responseModel;
	}

	@GetMapping("/emp/getList")
	public ResponseEntity<ResponseData> getAllEmp(){

		ResponseData  responseBody=new ResponseData();

		try {
			DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("dd-mm-YYYY");

			List<Employee> emp=employeeServiceImpl.listAll();

			/*
			 * List<EmployBean> list=new ArrayList<>();
			 * 
			 * for(Employee em:emp) { list.add(new EmployBean(em.getId(), em.getName(),
			 * dateFormatter.format(em.getBirthdate()))); }
			 */
			responseBody.setEmpList(emp);
			//responseBody.setPort(environment.getActiveProfiles().getClass().getFields().t);
			responseBody.setException(new ExceptionBody("SUCCESS", new Date(), "List of employees", HttpStatus.ACCEPTED));

		}
		catch(EmployCustomException e) {
			responseBody.setEmpList(new ArrayList<>());
			responseBody.setException(new ExceptionBody("ERROR", new Date(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));

		}
		return new ResponseEntity(responseBody, HttpStatus.ALREADY_REPORTED);

	}

	@PostMapping("/emp/addAccount")
	public EntityModel<ResponseData> addAccount( @RequestBody Account bean){

		ResponseData responseData=new ResponseData();
		EntityModel<ResponseData> responseModel=EntityModel.of(responseData);

		try {
			accountServiceImpl.addAccount(bean);
			responseData.setException(new ExceptionBody("SUCCESS", new Date(), "List of accounts", HttpStatus.CREATED));
		}
		catch( AccountNotFoundException e) {

			responseData.setException(new ExceptionBody("ERROR", new Date(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));

		}
		/*
		 * WebMvcLinkBuilder builder=linkTo(methodOn(this.getClass(), getAllEmp()));
		 * responseModel.add(builder.withRel("list-all-accounts"));
		 */
		return responseModel;
	}

	@GetMapping("/emp/getAccList")
	public ResponseEntity<ResponseData> getAllAccounts(){

		ResponseData  responseBody=new ResponseData();
		try {

			List<Account> emp=accountServiceImpl.listAll();
			responseBody.setAccList(emp);
			responseBody.setException(new ExceptionBody("SUCCESS", new Date(), "List of employees", HttpStatus.ACCEPTED));

		}
		catch(AccountNotFoundException e) {
			responseBody.setEmpList(new ArrayList<>());
			responseBody.setException(new ExceptionBody("ERROR", new Date(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
		}
		return new ResponseEntity(responseBody, HttpStatus.ALREADY_REPORTED);

	}



}
