package com.example.artspectrum.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor()
public class ArtAttribute {
	@Entity
	@Getter
	@Setter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@Table(name = "art_style")
	public static class ArtStyle {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Column(name = "style_name")
		private String name;
	}
	
	@Entity
	@Getter
	@Setter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@Table(name = "art_material")
	public static class Material {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Column(name = "material_name")
		private String name;
	}
	
	@Entity
	@Getter
	@Setter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@Table(name = "art_technique")
	public static class Technique {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Column(name = "techique_name")
		private String name;
	}
}
