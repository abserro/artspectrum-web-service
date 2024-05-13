package com.example.artspectrum.utils.exception;

public class UserAlreadyExists extends RuntimeException{
	public UserAlreadyExists(String message) {
		super(message);
	}
}
