package com.restwebservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class EmployExceptionHandler 
extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EmployCustomException.class)
	public final ResponseEntity<Object> handleAllCustomException
	(Exception ex, WebRequest request) throws Exception {
		ExceptionBody	response =new ExceptionBody("Exception", new Date() ,ex.getMessage(), HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleAllUserNotFoundException
	(Exception ex, WebRequest request) throws Exception {
		ExceptionBody	response =new ExceptionBody("USER NOT FOUND", new Date() ,ex.getMessage(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<Object> handleAllAccountNotFoundException
	(Exception ex, WebRequest request) throws Exception {
		ExceptionBody	response =new ExceptionBody("ACCOUNT NOT FOUND", new Date() ,ex.getMessage(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}




	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionBody	response =new ExceptionBody(ex.getMessage(), new Date() ,ex.getBindingResult().toString(), HttpStatus.NOT_ACCEPTABLE);

		return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
	}


}
