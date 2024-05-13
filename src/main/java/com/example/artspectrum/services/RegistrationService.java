package com.example.artspectrum.services;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import com.example.artspectrum.repositories.IUserRepository;
import com.example.artspectrum.utils.exception.UserAlreadyExists;
import com.example.artspectrum.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IUserRepository userRepository;
	public User createUser(UserDTO userDTO) {
		if (!isUsernameAvailable(userDTO.getLogin())) {
			throw new UserAlreadyExists("Login " + userDTO.getLogin() + " is not available");
		}
		if (!userDTO.getPassword().equals(userDTO.getPasswordConfirm())) {
			throw new IllegalArgumentException("Password and password confirmation do not match");
		}
		if (!isValidRoles(userDTO.getRoles())) {
			throw new IllegalArgumentException("Role is not valid");
		}
		User user = userDTO.toUser();
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRegistered(LocalDateTime.now());
		if (userDTO.getRoles().contains(Role.ROLE_ARTIST)) {
			user.setCountry(userDTO.getCountry());
			user.setCity(userDTO.getCity());
			user.setInfo(userDTO.getInfo());
		}
		
		return userRepository.save(user);
	}
	
	private boolean isUsernameAvailable(String login) {
		return userRepository.findByLogin(login).isEmpty();
	}
	
	private boolean isValidRoles(Set<Role> roles) {
		Set<Role> validRoles = new HashSet<>(Arrays.asList(Role.values()));
		return validRoles.containsAll(roles);
	}
}
