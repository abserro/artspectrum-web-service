package com.example.artspectrum.services;

import com.example.artspectrum.config.UserDetailsImpl;
import com.example.artspectrum.entities.User;
import com.example.artspectrum.repositories.IUserRepository;
import com.example.artspectrum.utils.exception.UserIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
    private IUserRepository userRepository;
	
	public Optional<User> getUserByLogin(String login) {
		Optional<User> user = userRepository.findByLogin(login);
		return Optional.ofNullable(user.orElseThrow(UserIdNotFoundException::new));
	}
	
	public User updateUser(Long userId, User updatedUser) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setLogin(updatedUser.getLogin());
			user.setPassword(updatedUser.getPassword());
			user.setFirstName(updatedUser.getFirstName());
			user.setLastName(updatedUser.getLastName());
			user.setUserAvatar(updatedUser.getUserAvatar());
			user.setDateBirth(updatedUser.getDateBirth());
			return userRepository.save(user);
		} else {
			throw new UsernameNotFoundException("User with id " + userId + " not found");
		}
	}
	
	public boolean deleteUserById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User with id " + userId + " not found");
		}
		User user = optionalUser.get();
		user.setRemoved(LocalDateTime.now());
		user.setBlocked(true);
		user.setLogin("REMOVED_" + user.getLogin());
		userRepository.save(user);
		return true;
	}
	
	@Override
	public UserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByLogin(login);
		return user.map(UserDetailsImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException(login + " not found"));
	}
}
