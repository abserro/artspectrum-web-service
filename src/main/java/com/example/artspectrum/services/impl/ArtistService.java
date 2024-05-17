package com.example.artspectrum.services.impl;

//import com.example.artspectrum.spec.ArtistSpecifications;
import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.Artwork;
import com.example.artspectrum.entities.User;
import com.example.artspectrum.repositories.IArtworkRepository;
import com.example.artspectrum.repositories.IUserRepository;
import com.example.artspectrum.services.IArtistService;
import com.example.artspectrum.utils.enums.Role;
import com.example.artspectrum.utils.exception.UserIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ArtistService implements IArtistService {
	private final IUserRepository userRepository;
	private final IArtworkRepository artworkRepository;
	
	@Autowired
	public ArtistService(IUserRepository userRepository, IArtworkRepository artworkRepository) {
		this.userRepository = userRepository;
		this.artworkRepository = artworkRepository;
	}
	
	@Override
	public Page<User> getAllArtists(Specification<User> spec, Pageable pageable) {
		return userRepository.findAll(spec, pageable);
	}
	
	@Override
	public User updateArtist(Long artistId, UserDTO artistDTO) {
		User artist = userRepository.findById(artistId)
				.orElseThrow(() -> new UserIdNotFoundException("Artist with ID " + artistId + " not found"));
		artist.setFirstName(artistDTO.getFirstName());
		artist.setLastName(artistDTO.getLastName());
		artist.setCountry(artistDTO.getCountry());
		artist.setCity(artistDTO.getCity());
		artist.setInfo(artistDTO.getInfo());
		return userRepository.save(artist);
	}
	
	@Override
	public Page<User> getUsersByRole(String role) {
		return userRepository.findByRoles(Role.valueOf(role.toUpperCase()), Pageable.unpaged());
	}
	
	@Override
	public Page<User> getUsersByRole(String role, Pageable pageable) {
		return userRepository.findByRoles(Role.valueOf(role.toUpperCase()), pageable);
	}
	
	@Override
	public void deleteArtistById(Long artistId) {
		userRepository.deleteById(artistId);
	}
	
	@Override
	public Page<User> searchArtists(String keyword, Pageable pageable) {
		return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword, pageable);
	}
	
	@Override
	public Page<User> getArtistsByCountry(String country, Pageable pageable) {
		return userRepository.findByCountryIgnoreCase(country, pageable);
	}
	
	@Override
	public Page<User> getArtistsByCity(String city, Pageable pageable) {
		return userRepository.findByCityIgnoreCase(city, pageable);
	}
	
	@Override
	public Page<User> getArtistsByTotalRating(double minRating, double maxRating, Pageable pageable) {
		return userRepository.findByTotalRatingBetween(minRating, maxRating, pageable);
	}
	
	@Override
	public Page<User> getArtistsByFullNameAndCountry(String firstName, String lastName, String country, Pageable pageable) {
		return userRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndCountryIgnoreCase(firstName, lastName, country, pageable);
	}
	
	@Override
	public Page<User> getArtistsByInfoContaining(String keyword, Pageable pageable) {
		return userRepository.findByInfoContainingIgnoreCase(keyword, pageable);
	}
	
	@Override
	public User getArtistById(Long artistId) {
		return userRepository.findById(artistId)
				.orElseThrow(() -> new UserIdNotFoundException("Artist with ID " + artistId + " not found"));
	}
	
	// Методы для получения художников с их произведениями
	@Override
	public Page<User> getArtistsWithArtworks(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	@Override
	public Page<Artwork> getArtistArtworks(Long artistId, Pageable pageable) {
		return artworkRepository.findByUserId(artistId, pageable);
	}
}

