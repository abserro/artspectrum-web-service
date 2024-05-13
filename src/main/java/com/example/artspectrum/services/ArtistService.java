package com.example.artspectrum.services;

import com.example.artspectrum.services.interfaces.IArtistService;
//import com.example.artspectrum.spec.ArtistSpecifications;
import org.springframework.stereotype.Service;

@Service
public class ArtistService implements IArtistService {
//	@Autowired
//	private IUserService userRepository;
//
//	@Override
//	public Page<User> getAllArtists(Specification<User> spec, Pageable pageable) {
//		return userRepository.findAll(spec, pageable);
//	}
//
//	@Override
//	public Page<User> searchArtists(String keyword, Pageable pageable) {
//		Specification<User> spec = UserSpecifications.byInfoContaining(keyword);
//		return userRepository.findAll(spec, pageable);
//	}
//
//	@Override
//	public Page<User> getArtistsWithArtworks(Pageable pageable) {
//		Specification<User> spec = ArtistSpecifications.withArtworks();
//		return userRepository.findAll(spec, pageable);
//	}
//
//	@Override
//	public Page<User> getArtistsByFullNameAndCountry(String firstName, String lastName, String country, Pageable pageable) {
//		Specification<User> spec = ArtistSpecifications.byFullNameAndCountry(firstName, lastName, country);
//		return userRepository.findAll(spec, pageable);
//	}
//
//	@Override
//	public Page<User> getArtistsByInfoContaining(String keyword, Pageable pageable) {
//		Specification<User> spec = ArtistSpecifications.byInfoContaining(keyword);
//		return userRepository.findAll(spec, pageable);
//	}
}

