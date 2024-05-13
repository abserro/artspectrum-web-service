package com.example.artspectrum.entities;

import com.example.artspectrum.utils.enums.OrderStatus;
import com.example.artspectrum.utils.enums.PaymentType;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders", indexes = {
		@Index(name = "idx_user_id", columnList = "user_id")
})
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@Column(name = "order_date_time", nullable = false)
	private LocalDateTime orderDateTime;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(name = "total_cost", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT 0.00")
	private BigDecimal totalCost = BigDecimal.ZERO;
	
	@Column(name = "payment_method")
	@Enumerated(EnumType.STRING)
	private PaymentType.Method paymentMethod;
	
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentType.Status paymentStatus;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
}
