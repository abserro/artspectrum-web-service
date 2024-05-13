package com.example.artspectrum.services.admin;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAdminArtistService extends IAdminService <User, UserDTO> {
	Page<User> getArtistByFullName(String firstName, String lastName, Pageable pageable);
	void blockArtist(Long userId);
	void unBlockArtist(Long userId);
	UserDetails getCurrentAdmin();
	long countArtists();
}
