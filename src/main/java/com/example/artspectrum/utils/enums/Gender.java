package com.example.artspectrum.utils.enums;

public enum Gender {
	MALE("MALE", 0),
    FEMALE("FEMALE", 1);
	
    private final String nameGender;
	private final int idGender;
	
	private Gender(String nameGender, int idGender) {
        this.nameGender = nameGender;
        this.idGender = idGender;
    }
}
