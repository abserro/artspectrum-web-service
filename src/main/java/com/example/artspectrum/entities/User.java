package com.example.artspectrum.entities;

import com.example.artspectrum.utils.enums.Gender;
import com.example.artspectrum.utils.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tuser", indexes = {
		@Index(name = "idx_user_id", columnList = "id"),
		@Index(name = "idx_user_login", columnList = "login", unique = true)
})
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
	@SequenceGenerator(name = "user_seq_generator", sequenceName = "tuser_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	@Email
	@Size(min = 2, max = 50)
	@Column(name = "login", nullable = false, unique = true)
	private String login;
	
	@Size(min = 6)
	@NotBlank
	@Column(name = "password", nullable = false)
	private String password;
	
	@NotBlank
	@Size(min = 2, max = 50)
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotBlank
	@Size(min = 2, max = 50)
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Size(max = 255)
	@Column(name = "user_avatar")
	private String userAvatar;
	
	@Column(name = "removed")
	private LocalDateTime removed;
	
	@Column(name = "blocked", columnDefinition = "boolean default false")
	private boolean blocked;
	
	@Past
	@Column(name = "date_birth")
	private LocalDate dateBirth;
	
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@NotNull
	@Column(name = "registered", nullable = false)
	private LocalDateTime registered;
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
	@Size(min = 2, max = 255)
	@Column(name = "info")
	private String info;
	
	@Size(min = 2, max = 50)
	@Column(name = "country")
	private String country;
	
	@Size(min = 2, max = 50)
	@Column(name = "city")
	private String city;
	
	@Column(name = "total_rating")
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "5.0")
	private double totalRating;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Artwork> artworks;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	@JsonIgnore
	public void setPassword(String password) {
		this.password = password;
	}
}
