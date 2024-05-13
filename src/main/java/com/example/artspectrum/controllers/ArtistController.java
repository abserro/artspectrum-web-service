package com.example.artspectrum.controllers;

import com.example.artspectrum.entities.User;
import com.example.artspectrum.services.interfaces.IArtistService;
import com.example.artspectrum.services.interfaces.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/api/v1/artspectrum/artists")
//@SecurityRequirement(name = "Bearer Authentication")
public class ArtistController {
//	private final IUserService userService;
//
//	@Autowired
//	public ArtistController(IUserService userService) {
//		this.userService = userService;
//	}
//
//	@GetMapping("/all")
//	public ResponseEntity<Page<User>> getAllArtists(@RequestParam(required = false) String country,
//	                                                @RequestParam(required = false) String city,
//	                                                @RequestParam(required = false) Double minRating,
//	                                                @RequestParam(required = false) Double maxRating,
//	                                                @RequestParam(required = false) LocalDate dateBirthFrom,
//	                                                @RequestParam(required = false) LocalDate dateBirthTo,
//	                                                @RequestParam(required = false) LocalDate dateRegisterFrom,
//	                                                @RequestParam(required = false) LocalDate dateRegisterTo,
//	                                                Pageable pageable) {
//		Specification<User> spec = ArtistSpecifications.filterArtists(country, city, minRating, maxRating,
//				dateBirthFrom, dateBirthTo, dateRegisterFrom, dateRegisterTo);
//		Page<User> artists = artistService.getAllArtists(spec, pageable);
//		return artists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artists);
//	}
//
//	@GetMapping("/{artistId}")
//	public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId) {
//		Artist artist = artistService.getArtistById(artistId);
//		return ResponseEntity.ok(artist);
//	}
//
//	@PostMapping
//	public ResponseEntity<Artist> createArtist(@RequestBody ArtistDTO artistDTO) {
//		Artist createdArtist = artistService.createArtist(artistDTO);
//		return ResponseEntity.status(HttpStatus.CREATED).body(createdArtist);
//	}
//
//	@PutMapping("/{artistId}")
//	public ResponseEntity<Artist> updateArtist(@PathVariable Long artistId, @RequestBody ArtistDTO artistDTO) {
//		Artist updatedArtist = artistService.updateArtist(artistId, artistDTO);
//		return ResponseEntity.ok(updatedArtist);
//	}
//
//	@DeleteMapping("/{artistId}")
//	public ResponseEntity<Void> deleteArtistById(@PathVariable Long artistId) {
//		artistService.deleteArtistById(artistId);
//		return ResponseEntity.ok().build();
//	}
//
//	@GetMapping("/search")
//	public ResponseEntity<Page<Artist>> searchArtists(@RequestParam String keyword, Pageable pageable) {
//		Page<Artist> artists = artistService.searchArtists(keyword, pageable);
//		return artists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artists);
//	}
//
//	@GetMapping("/country")
//	public ResponseEntity<Page<Artist>> getArtistsByCountry(@RequestParam String country, Pageable pageable) {
//		Page<Artist> artists = artistService.getArtistsByCountry(country, pageable);
//		return artists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artists);
//	}
//
//	@GetMapping("/city")
//	public ResponseEntity<Page<Artist>> getArtistsByCity(@RequestParam String city, Pageable pageable) {
//		Page<Artist> artists = artistService.getArtistsByCity(city, pageable);
//		return artists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artists);
//	}
//
//	@GetMapping("/totalRating")
//	public ResponseEntity<Page<Artist>> getArtistsByTotalRating(@RequestParam double minRating,
//	                                                            @RequestParam double maxRating,
//	                                                            Pageable pageable) {
//		Page<Artist> artists = artistService.getArtistsByTotalRating(minRating, maxRating, pageable);
//		return artists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artists);
//	}
//
//	@GetMapping("/withArtworks")
//	public ResponseEntity<Page<Artist>> getArtistsWithArtworks(Pageable pageable) {
//		Page<Artist> artists = artistService.getArtistsWithArtworks(pageable);
//		return artists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artists);
//	}
//
//	@GetMapping("/fullNameAndCountry")
//	public ResponseEntity<Page<Artist>> getArtistsByFullNameAndCountry(@RequestParam String firstName,
//	                                                                   @RequestParam String lastName,
//	                                                                   @RequestParam String country,
//	                                                                   Pageable pageable) {
//		Page<Artist> artists = artistService.getArtistsByFullNameAndCountry(firstName, lastName, country, pageable);
//		return artists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artists);
//	}
//
//	@GetMapping("/infoContaining")
//	public ResponseEntity<Page<Artist>> getArtistsByInfoContaining(@RequestParam String keyword, Pageable pageable) {
//		Page<Artist> artists = artistService.getArtistsByInfoContaining(keyword, pageable);
//		return artists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artists);
//	}
}
