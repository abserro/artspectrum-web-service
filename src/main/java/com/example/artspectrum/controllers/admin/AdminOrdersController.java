package com.example.artspectrum.controllers.admin;

import com.example.artspectrum.dto.OrderDTO;
import com.example.artspectrum.entities.Order;
import com.example.artspectrum.entities.OrderItem;
import com.example.artspectrum.services.admin.AdminOrderService;
import com.example.artspectrum.utils.OrderFilterCriteria;
import com.example.artspectrum.utils.OrderSpecifications;
import com.example.artspectrum.utils.enums.OrderStatus;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artspectrum/admin/orders")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminOrdersController extends AbstractAdminController<Order, OrderDTO, OrderFilterCriteria> {
	
	private final AdminOrderService adminOrdersService;
	
	@Autowired
	public AdminOrdersController(AdminOrderService adminOrdersService) {
		super(adminOrdersService);
		this.adminOrdersService = adminOrdersService;
	}
	
	@Override
	@GetMapping("/all")
	public ResponseEntity<Page<Order>> getAll(@PageableDefault(size = 10) Pageable pageable,
	                                                @ModelAttribute OrderFilterCriteria filterCriteria) {
		Specification<Order> spec = OrderSpecifications.buildFilterCriteria(filterCriteria);
		Page<Order> orders = adminOrdersService.getAll(spec, pageable);
		return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countOrders() {
		long count = adminOrdersService.countOrders();
		return ResponseEntity.ok(count);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
		Order order = adminOrdersService.getById(orderId);
		return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{orderId}/items")
	public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable Long orderId) {
		Order order = adminOrdersService.getById(orderId);
		if (order != null) {
			List<OrderItem> orderItems = order.getOrderItems();
			return ResponseEntity.ok(orderItems);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{orderId}/status")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId,
	                                               @RequestParam OrderStatus status) {
		Order updatedOrder = adminOrdersService.updateOrderStatus(orderId, status);
		return updatedOrder != null ? ResponseEntity.ok(updatedOrder) : ResponseEntity.notFound().build();
	}
}


