package com.example.artspectrum.services.admin;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import com.example.artspectrum.repositories.IUserRepository;
import com.example.artspectrum.utils.exception.LoginNotFoundException;
import com.example.artspectrum.utils.exception.UserIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminUsersService implements IAdminUsersService {
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public Page<User> getAll(Specification<User> spec, Pageable pageable) {
		return userRepository.findAll(spec, pageable);
	}
	
	@Override
	public User getById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserIdNotFoundException("User with ID " + id + " not found"));
	}
	
	@Override
	public User create(User entity) {
		return null;
	}
	
	@Override
	public User update(Long id, UserDTO userDTO) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new UserIdNotFoundException("User with ID " + id + " not found"));
		existingUser.setLogin(userDTO.getLogin());
		existingUser.setFirstName(userDTO.getFirstName());
		existingUser.setLastName(userDTO.getLastName());
		existingUser.setUserAvatar(userDTO.getUserAvatar());
		existingUser.setDateBirth(userDTO.getDateBirth());
		existingUser.setGender(userDTO.getGender());
		return userRepository.save(existingUser);
	}
	
	@Override
	public boolean delete(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User with id " + id + " not found");
		}
		User user = optionalUser.get();
		user.setRemoved(LocalDateTime.now());
		user.setBlocked(true);
		user.setLogin("REMOVED_" + user.getLogin());
		userRepository.save(user);
		return true;
	}
	
	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	@Override
	public User getUserByLogin(String login) {
		return userRepository.findByLogin(login)
				.orElseThrow(() -> new LoginNotFoundException("User with login " + login + " not found"));
	}

	@Override
	public void blockUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + " not found"));
		user.setBlocked(true);
		user.setLogin("BLOCKED_" + user.getLogin());
		userRepository.save(user);
	}

	@Override
	public void unblockUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + " not found"));
		user.setBlocked(false);
		if (user.getLogin().startsWith("BLOCKED_")) {
			user.setLogin(user.getLogin().substring(8)); // Удаляем первые 8 символов
		}
		userRepository.save(user);
	}
	
	@Override
	public boolean isUsernameAvailable(String username) {
		return !userRepository.existsByLogin(username);
	}

	@Override
	public UserDetails getCurrentAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserDetails) principal;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean isAdmin(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + " not found"));
		UserDetails currentUser = getCurrentAdmin();
		return currentUser != null && currentUser.getUsername().equals(user.getLogin());
	}
	
	@Override
	public long countUsers() {
		return userRepository.count();
	}
}
