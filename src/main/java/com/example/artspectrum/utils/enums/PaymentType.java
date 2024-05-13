package com.example.artspectrum.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class PaymentType {
	@Getter
	@RequiredArgsConstructor
	public enum Method {
		CASH("Cash"),
		CREDIT_CARD("Credit Card"),
		DEBIT_CARD("Debit Card"),
		PAYPAL("PayPal"),
		OTHER("Other");

		private final String displayName;
	}
	
	@Getter
	@RequiredArgsConstructor
	public enum Status {
		PENDING("Pending"),
		COMPLETED("Completed"),
		FAILED("Failed"),
		CANCELED("Canceled");

		private final String displayName;
	}
}
