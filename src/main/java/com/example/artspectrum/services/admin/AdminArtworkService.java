package com.example.artspectrum.services.admin;

import com.example.artspectrum.dto.ArtworkDTO;
import com.example.artspectrum.entities.Artwork;
import com.example.artspectrum.repositories.IArtworkRepository;
import com.example.artspectrum.utils.enums.ArtworkStatus;
import com.example.artspectrum.utils.exception.ArtworkNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AdminArtworkService implements IAdminArtworkService {
	
	private final IArtworkRepository artworkRepository;
	
	@Autowired
	public AdminArtworkService(IArtworkRepository artworkRepository) {
		this.artworkRepository = artworkRepository;
	}
	
	@Override
	public Page<Artwork> getAll(Specification<Artwork> specification, Pageable pageable) {
		return artworkRepository.findAll(specification, pageable);
	}
	
	@Override
	public Artwork getById(Long id) {
		return artworkRepository.findById(id).orElse(null);
	}
	
	@Override
	public Artwork create(Artwork entity) {
		return artworkRepository.save(entity);
	}
	
	@Override
	public Artwork update(Long id, ArtworkDTO dto) {
		Artwork existingArtwork = artworkRepository.findById(id).orElse(null);
		if (existingArtwork != null) {
			existingArtwork.setTitle(dto.getTitle());
			existingArtwork.setDescription(dto.getDescription());
			existingArtwork.setRecommendation(dto.isRecommendation());
			return artworkRepository.save(existingArtwork);
		}
		return null;
	}
	
	@Override
	public boolean delete(Long id) {
		Artwork existingArtwork = artworkRepository.findById(id).orElse(null);
		if (existingArtwork != null) {
			artworkRepository.delete(existingArtwork);
			return true;
		}
		return false;
	}
	
	@Override
	public void deleteAll() {
		artworkRepository.deleteAll();
	}
	
	@Override
	public long countArtworks() {
		return artworkRepository.count();
	}
	
	
	@Override
	public Page<Artwork> getArtworksByUser(Long userId, Pageable pageable) {
		return artworkRepository.findByUserId(userId, pageable);
	}
	
	@Override
	public Page<Artwork> getArtworksByStatus(ArtworkStatus status, Pageable pageable) {
		return artworkRepository.findByStatus(status, pageable);
	}
	
	@Override
	@Transactional
	public Artwork updateArtworkStatus(Long artworkId, ArtworkStatus newStatus) {
		Artwork artwork = artworkRepository.findById(artworkId)
				.orElseThrow(() -> new ArtworkNotFoundException("Artwork" + artworkId + "not found"));
		artwork.setStatus(newStatus);
		return artworkRepository.save(artwork);
	}
	
}
