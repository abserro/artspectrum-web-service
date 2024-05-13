package com.example.artspectrum.services.admin;

import com.example.artspectrum.dto.ArtworkDTO;
import com.example.artspectrum.entities.Artwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AdminArtworkService implements IAdminArtworkService{
	@Override
	public Page<Artwork> getAll(Specification<Artwork> specification, Pageable pageable) {
		return null;
	}
	
	@Override
	public Artwork getById(Long id) {
		return null;
	}
	
	@Override
	public Artwork create(Artwork entity) {
		return null;
	}
	
	@Override
	public Artwork update(Long id, ArtworkDTO dto) {
		return null;
	}
	
	@Override
	public boolean delete(Long id) {
		return false;
	}
	
	@Override
	public void deleteAll() {
	
	}
}
