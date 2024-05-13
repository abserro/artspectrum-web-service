package com.example.artspectrum.utils;

import com.example.artspectrum.utils.enums.Gender;
import com.example.artspectrum.utils.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserFilterCriteria {
	private String login;
	private String firstName;
	private String lastName;
	private Boolean blocked;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateBirthFrom;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateBirthTo;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateRegisterFrom;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateRegisterTo;
	private Gender gender;
	private String info;
	private String country;
	private String city;
	private Double minRating;
	private Double maxRating;
	private Role role;
	private String sortField;
	private String sortDirection;
}

