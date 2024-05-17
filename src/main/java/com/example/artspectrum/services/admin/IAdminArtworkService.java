package com.example.artspectrum.services.admin;

import com.example.artspectrum.dto.ArtworkDTO;
import com.example.artspectrum.entities.Artwork;
import com.example.artspectrum.utils.enums.ArtworkStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdminArtworkService extends IAdminService<Artwork, ArtworkDTO> {
	long countArtworks();
	Page<Artwork> getArtworksByUser(Long userId, Pageable pageable);
	Page<Artwork> getArtworksByStatus(ArtworkStatus status, Pageable pageable);
	Artwork updateArtworkStatus(Long artworkId, ArtworkStatus newStatus);
}
