package com.example.artspectrum.dto;

import com.example.artspectrum.entities.Order;
import com.example.artspectrum.entities.OrderItem;
import com.example.artspectrum.entities.User;
import com.example.artspectrum.utils.enums.OrderStatus;
import com.example.artspectrum.utils.enums.PaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
	private User user_id;
	private LocalDateTime orderDateTime;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	private BigDecimal totalCost = BigDecimal.ZERO;
	@Enumerated(EnumType.STRING)
	private PaymentType.Method paymentMethod;
	@Enumerated(EnumType.STRING)
	private PaymentType.Status paymentStatus;
	private List<OrderItem> orderItems = new ArrayList<>();
	
	public static OrderDTO fromEntity(Order order) {
		OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser_id(order.getUser());
        orderDTO.setOrderDateTime(order.getOrderDateTime());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalCost(order.getTotalCost());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setOrderItems(order.getOrderItems());
        return orderDTO;
	}
}
