package com.restwebservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployCustomException  extends Exception{

	public EmployCustomException() {

		super();
	}

}
