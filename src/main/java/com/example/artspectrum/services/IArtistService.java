package com.example.artspectrum.services;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.Artwork;
import com.example.artspectrum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

public interface IArtistService {
	Page<User> getAllArtists(Specification<User> spec, Pageable pageable);
	User updateArtist(Long artistId, UserDTO artistDTO);
	Page<User> getUsersByRole(String role);
	Page<User> getUsersByRole(String role, Pageable pageable);
	void deleteArtistById(Long artistId);
	Page<User> searchArtists(String keyword, Pageable pageable);
	Page<User> getArtistsByCountry(String country, Pageable pageable);
	Page<User> getArtistsByCity(String city, Pageable pageable);
	Page<User> getArtistsByTotalRating(double minRating, double maxRating, Pageable pageable);
	Page<User> getArtistsWithArtworks(Pageable pageable);
	Page<User> getArtistsByFullNameAndCountry(String firstName, String lastName, String country, Pageable pageable);
	Page<User> getArtistsByInfoContaining(String keyword, Pageable pageable);
	User getArtistById(Long artistId);
	Page<Artwork> getArtistArtworks(Long artistId, Pageable pageable);
}
