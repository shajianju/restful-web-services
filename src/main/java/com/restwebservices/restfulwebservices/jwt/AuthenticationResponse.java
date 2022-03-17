package com.restwebservices.restfulwebservices.jwt;

public class AuthenticationResponse {
	
	private final String token;

	public String getToken() {
		return token;
	}

	public AuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	
	

}
