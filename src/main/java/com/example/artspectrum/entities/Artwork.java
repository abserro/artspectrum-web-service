package com.example.artspectrum.entities;

import com.example.artspectrum.utils.enums.ArtworkStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artwork", indexes = {
		@Index(name = "idx_artwork_id", columnList = "id"),
        @Index(name = "idx_artwork_title", columnList = "title", unique = true)
})
public class Artwork {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artwork_seq_generator")
	@SequenceGenerator(name = "artwork_seq_generator", sequenceName = "artwork_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@NotBlank
	@Size(min = 2, max = 100)
	@Column(name = "title", nullable = false)
	private String title;
	
	@Size(max = 255)
	@Column(name = "description")
	private String description;
	
	@Column(name = "recommendation")
	private boolean recommendation;
	
	@NotNull
	@Column(name = "date_creation", nullable = false)
	private LocalDate dateCreation;
	
	@NotNull
	@Column(name = "date_addition", nullable = false)
	private LocalDate dateAddition;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ArtworkStatus status;
	
	@Column(name = "original")
	private boolean original;
	
	@Column(name = "possible_remake")
	private boolean possibleRemake;
	
	@Column(name = "frame")
	private boolean frame;
	
	@Column(name = "size_height")
	private double sizeHeight;
	
	@Column(name = "size_width")
	private double sizeWidth;
	
	@Column(name = "size_depth")
	private double sizeDepth;
	
	@Column(name = "price")
	BigDecimal price;
	
	@NotBlank
	@Size(min = 2, max = 255)
	@Column(name = "url_image", nullable = false)
	private String urlImage;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "artwork_style",
			joinColumns = @JoinColumn(name = "artwork_id"),
			inverseJoinColumns = @JoinColumn(name = "style_id"))
	private Set<ArtAttribute.ArtStyle> styles = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "artwork_material",
			joinColumns = @JoinColumn(name = "artwork_id"),
			inverseJoinColumns = @JoinColumn(name = "material_id"))
	private Set<ArtAttribute.Material> materials = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "artwork_technique",
			joinColumns = @JoinColumn(name = "artwork_id"),
			inverseJoinColumns = @JoinColumn(name = "technique_id"))
	private Set<ArtAttribute.Technique> techniques = new HashSet<>();
}