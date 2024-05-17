package com.example.artspectrum.utils.exception;

public class OrderNotFoundException extends RuntimeException{
	public OrderNotFoundException(String orderNotFound) {
		super(orderNotFound);
	}
}
