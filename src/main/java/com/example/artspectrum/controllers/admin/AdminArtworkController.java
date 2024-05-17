package com.example.artspectrum.controllers.admin;

import com.example.artspectrum.dto.ArtworkDTO;
import com.example.artspectrum.entities.ArtAttribute;
import com.example.artspectrum.entities.Artwork;
import com.example.artspectrum.services.admin.AdminArtworkService;
import com.example.artspectrum.utils.ArtworkFilterCriteria;
import com.example.artspectrum.utils.ArtworkSpecifications;
import com.example.artspectrum.utils.enums.ArtworkStatus;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/artspectrum/admin/artworks")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminArtworkController extends AbstractAdminController<Artwork, ArtworkDTO, ArtworkFilterCriteria>{
	
	private final AdminArtworkService adminArtworkService;
	
	@Autowired
	public AdminArtworkController(AdminArtworkService adminArtworkService) {
		super(adminArtworkService);
		this.adminArtworkService = adminArtworkService;
	}
	
	@Override
	@GetMapping("/all")
	public ResponseEntity<Page<Artwork>> getAll(@PageableDefault(size = 10) Pageable pageable,
	                                                    @ModelAttribute ArtworkFilterCriteria criteria) {
		Specification<Artwork> spec = ArtworkSpecifications.buildFilterCriteria(criteria);
		Page<Artwork> artworks = adminArtworkService.getAll(spec, pageable);
		return artworks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artworks);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countArtworks() {
		long count = adminArtworkService.countArtworks();
		return ResponseEntity.ok(count);
	}
	
	@GetMapping("/byUser/{userId}")
	public ResponseEntity<Page<Artwork>> getArtworksByUser(@PathVariable Long userId,
	                                                       @PageableDefault(size = 10) Pageable pageable) {
		Page<Artwork> artworksByUser = adminArtworkService.getArtworksByUser(userId, pageable);
		return artworksByUser.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artworksByUser);
	}
	
	@GetMapping("/byStatus/{status}")
	public ResponseEntity<Page<Artwork>> getArtworksByStatus(@PathVariable ArtworkStatus status,
	                                                         @PageableDefault(size = 10) Pageable pageable) {
		Page<Artwork> artworksByStatus = adminArtworkService.getArtworksByStatus(status, pageable);
		return artworksByStatus.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(artworksByStatus);
	}
	
	@PutMapping("/{artworkId}/status")
	public ResponseEntity<?> updateArtworkStatusWithRequestParam(@PathVariable Long artworkId,
	                                                             @RequestParam("newStatus") ArtworkStatus newStatus) {
		Artwork updatedArtwork = adminArtworkService.updateArtworkStatus(artworkId, newStatus);
		if (updatedArtwork != null) {
			return ResponseEntity.ok(updatedArtwork);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
