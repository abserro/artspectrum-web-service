package com.example.artspectrum.controllers;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import com.example.artspectrum.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/artspectrum/sign-up")
public class RegistrationController {
	private final RegistrationService registrationService;
	
	@Autowired
	public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
	
	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
		User newUser = registrationService.createUser(user);
		return ResponseEntity.ok(newUser);
	}
}
