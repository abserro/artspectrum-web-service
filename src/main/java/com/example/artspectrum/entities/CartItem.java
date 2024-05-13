package com.example.artspectrum.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cart_items")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "artwork_id", nullable = false)
	private Artwork painting;
	
	@Column(name = "quantity", columnDefinition = "int default 1")
	private int quantity;
}
