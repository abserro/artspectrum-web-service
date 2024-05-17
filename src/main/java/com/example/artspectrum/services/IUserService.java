package com.example.artspectrum.services;

import com.example.artspectrum.dto.UpdateProfileRequest;
import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IUserService {
	Page<User> getAllArtists(Specification<User> spec, Pageable pageable);
	Optional<User> getUserByLogin(String username);
	User updateUser(Long userId, User user);
	boolean deleteUserById(Long userId);
	boolean isUsernameAvailable(String username);
	Page<User> findAll(Specification<User> spec, Pageable pageable);
	Optional<Object> findById(Long artistId);
	
	boolean changePassword(String currentUsername, String oldPassword, String newPassword);
	
	boolean updateProfile(String currentUsername, UpdateProfileRequest request);
	
	boolean updateAvatar(String currentUsername, MultipartFile file);
}
