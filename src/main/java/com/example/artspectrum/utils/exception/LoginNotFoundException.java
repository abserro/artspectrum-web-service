package com.example.artspectrum.utils.exception;

public class LoginNotFoundException extends RuntimeException{
	public LoginNotFoundException(String message) {
		super(message);
	}
}
