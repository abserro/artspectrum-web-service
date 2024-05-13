package com.example.artspectrum.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
	CREATED("Создан"),
	PROCESSING("Обрабатывается"),
	SHIPPED("Отправлен"),
	DELIVERED("Доставлен"),
	CANCELLED("Отменен");;
	
	private final String statusName;
}
