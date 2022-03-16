package com.restwebservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ExceptionBody {

	private String message;
	private Date timestamp;
	private String description;
	private HttpStatus httpStatus;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public ExceptionBody(String message, Date timestamp, String description, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.description = description;
		this.httpStatus = httpStatus;
	}
	public ExceptionBody() {
		super();
	}





}
