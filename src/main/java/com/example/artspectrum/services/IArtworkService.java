package com.example.artspectrum.services;

import com.example.artspectrum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArtworkService {
	Page<User> getAllArtworks(Pageable pageable);
	
	List<User> getArtworksByArtist(Long artistId);
}
