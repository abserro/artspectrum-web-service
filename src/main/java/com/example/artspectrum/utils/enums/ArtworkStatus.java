package com.example.artspectrum.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ArtworkStatus {
	AVAILABLE("Доступно"),
	SOLD("Продано"),
	RESERVED("Зарезервировано"),
	UNDER_REVIEW("На рассмотрении"),
	OUT_OF_STOCK("Нет в наличии"),
	ARCHIVED("Архивировано");
	
	private final String statusName;
}

