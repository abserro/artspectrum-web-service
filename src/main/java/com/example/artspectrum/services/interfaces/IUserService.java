package com.example.artspectrum.services.interfaces;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface IUserService {
	Page<User> getAllUsers(Pageable pageable);
	Optional<User> getUserById(Long userId);
	Optional<User> getUserByLogin(String username);
	User createUser(UserDTO user);
	User updateUser(Long userId, User user);
	boolean deleteUserById(Long userId);
	boolean isUsernameAvailable(String username);
	Page<User> findAll(Specification<User> spec, Pageable pageable);
	Optional<Object> findById(Long artistId);
}
