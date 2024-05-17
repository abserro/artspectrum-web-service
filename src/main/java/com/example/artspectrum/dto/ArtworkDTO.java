package com.example.artspectrum.dto;

import com.example.artspectrum.utils.enums.ArtworkStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class ArtworkDTO {
	private Long userId;
	@NotBlank
	@Size(min = 2, max = 100)
	private String title;
	@Size(max = 255)
	private String description;
	private boolean recommendation;
	@NotNull
	private LocalDate dateCreation;
	@NotNull
	private LocalDate dateAddition;
	private ArtworkStatus status;
	private boolean original;
	private boolean possibleRemake;
	private boolean frame;
	private double sizeHeight;
	private double sizeWidth;
	private double sizeDepth;
	private BigDecimal price;
	@NotBlank
	@Size(min = 2, max = 255)
	private String urlImage;
}
