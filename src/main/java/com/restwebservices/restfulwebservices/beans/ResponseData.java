package com.restwebservices.restfulwebservices.beans;

import java.util.List;

import com.restwebservices.restfulwebservices.exception.ExceptionBody;
import com.restwebservices.restfulwebservices.models.Account;
import com.restwebservices.restfulwebservices.models.Employee;

public class ResponseData {
	
	private List<Employee> empList;
	
	private List<Account> accList;
	
	private ExceptionBody  exception;
	
	private String port;
	
	private String restApiName;
	
	


	public List<Account> getAccList() {
		return accList;
	}

	public void setAccList(List<Account> accList) {
		this.accList = accList;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public ExceptionBody getException() {
		return exception;
	}

	public void setException(ExceptionBody exception) {
		this.exception = exception;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getRestApiName() {
		return restApiName;
	}

	public void setRestApiName(String restApiName) {
		this.restApiName = restApiName;
	}
	
	
	
	

}
