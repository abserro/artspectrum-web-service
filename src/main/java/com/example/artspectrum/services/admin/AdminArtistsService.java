package com.example.artspectrum.services.admin;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import com.example.artspectrum.repositories.IUserRepository;
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
public class AdminArtistsService implements IAdminArtistService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public Page<User> getArtistByFullName(String firstName, String lastName, Pageable pageable) {
		
		return userRepository.findByFirstNameAndLastName(firstName, lastName, pageable);
	}
	
	@Override
	public void blockArtist(Long userId) {
		User artist = userRepository.findById(userId)
				.orElseThrow(() -> new UserIdNotFoundException("Artist with ID " + userId + " not found"));
		artist.setBlocked(true);
		userRepository.save(artist);
	}
	
	@Override
	public void unBlockArtist(Long userId) {
		User artist = userRepository.findById(userId)
				.orElseThrow(() -> new UserIdNotFoundException("Artist with ID " + userId + " not found"));
		artist.setBlocked(false);
		userRepository.save(artist);
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
	public long countArtists() {
		return userRepository.count();
	}
	
	@Override
	public Page<User> getAll(Specification<User> specification, Pageable pageable) {
		return userRepository.findAll(specification, pageable);
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
		existingUser.setInfo(userDTO.getInfo());
		existingUser.setCity(userDTO.getCity());
		existingUser.setCountry(userDTO.getCountry());
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
}
