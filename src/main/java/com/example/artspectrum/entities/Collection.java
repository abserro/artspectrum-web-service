package com.example.artspectrum.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "collection")
public class Collection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private User user;
	
	@ManyToMany
	@JoinTable(
			name = "collection_artworks",
			joinColumns = @JoinColumn(name = "collection_id"),
			inverseJoinColumns = @JoinColumn(name = "painting_id")
	)
	private List<Artwork> artworks = new ArrayList<>();
}
