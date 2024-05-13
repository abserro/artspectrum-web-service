package com.example.artspectrum.utils.exception;

public class UserIdNotFoundException extends RuntimeException{
	public UserIdNotFoundException(String message) {
		super(message);
	}
	
	public UserIdNotFoundException() {
		
    }
}
