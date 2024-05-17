package com.example.artspectrum.controllers.admin;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import com.example.artspectrum.services.admin.IAdminUsersService;
import com.example.artspectrum.utils.UserSpecifications;
import com.example.artspectrum.utils.UserFilterCriteria;
import com.example.artspectrum.utils.enums.Gender;
import com.example.artspectrum.utils.enums.Role;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/artspectrum/admin/users")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminUsersController extends AbstractAdminController<User, UserDTO, UserFilterCriteria> {
	private final IAdminUsersService adminService;
	
	@Autowired
	public AdminUsersController(IAdminUsersService adminService) {
		super(adminService);
		this.adminService = adminService;
	}
	
	@Override
	@GetMapping("/all")
	public ResponseEntity<Page<User>> getAll(@PageableDefault(size = 10) Pageable pageable,
	                                              @ModelAttribute UserFilterCriteria filterCriteria) {
		Specification<User> spec = UserSpecifications.buildFilterCriteria(filterCriteria);
		Page<User> users = adminService.getAll(spec, pageable);
		return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
	}
	
	@GetMapping("/{userId}/admin")
	public ResponseEntity<Boolean> isAdmin(@PathVariable Long userId) {
		boolean isAdmin = adminService.isAdmin(userId);
		return ResponseEntity.ok(isAdmin);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countUsers() {
		long count = adminService.countUsers();
		return ResponseEntity.ok(count);
	}
}
