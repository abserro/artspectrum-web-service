package com.example.artspectrum.dto;

import com.example.artspectrum.entities.User;
import com.example.artspectrum.utils.enums.Gender;
import com.example.artspectrum.utils.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class UserDTO {
	
	@NotBlank
	@Size(min = 2, max = 50)
	private String login;
	
	@Size(min = 6)
	@NotBlank
	@JsonProperty
	private String password;
	
	private String passwordConfirm;
	
	@NotBlank
	@Size(min = 2, max = 50)
	private String firstName;
	
	@NotBlank
	@Size(min = 2, max = 50)
	private String lastName;
	
	@Size(max = 255)
	private String userAvatar;
	
	@Past
	private LocalDate dateBirth;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
	@Size(min = 2, max = 255)
	private String info;
	
	@Size(min = 2, max = 50)
	private String country;
	
	@Size(min = 2, max = 50)
	private String city;
	
	public User toUser() {
		User user = new User();
		user.setLogin(login);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserAvatar(userAvatar);
		user.setDateBirth(dateBirth);
		user.setGender(gender);
		user.setRoles(roles);
		user.setInfo(info);
		user.setCountry(country);
		user.setCity(city);
		return user;
	}
}
