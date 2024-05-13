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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/artspectrum/admin/users")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminUsersController extends AbstractAdminController<User, UserDTO> {
	private final IAdminUsersService adminService;
	
	@Autowired
	public AdminUsersController(IAdminUsersService adminService) {
		super(adminService);
		this.adminService = adminService;
	}
	
	@GetMapping("/all/{pageNumber}")
	public ResponseEntity<Page<User>> getAllUsers(@PathVariable int pageNumber,
	                                              @RequestParam(defaultValue = "10") int size,
	                                              @RequestParam(required = false) String login,
	                                              @RequestParam(required = false) String firstName,
	                                              @RequestParam(required = false) String lastName,
	                                              @RequestParam(defaultValue = "false") boolean blocked,
	                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateBirthFrom,
	                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateBirthTo,
	                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRegisterFrom,
	                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRegisterTo,
	                                              @RequestParam(required = false) Gender gender,
	                                              @RequestParam(required = false) String info,
	                                              @RequestParam(required = false) String country,
	                                              @RequestParam(required = false) String city,
	                                              @RequestParam(required = false) Double minRating,
	                                              @RequestParam(required = false) Double maxRating,
	                                              @RequestParam(required = false) String roleName,
	                                              @RequestParam(required = false) String sortField,
	                                              @RequestParam(defaultValue = "asc") String sortDirection) {
		Pageable pageable;
		if (sortField != null && !sortField.isEmpty()) {
			Sort sort = Sort.by(sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
			pageable = PageRequest.of(pageNumber - 1, size, sort);
		} else {
			pageable = PageRequest.of(pageNumber - 1, size);
		}
		
		UserFilterCriteria filterCriteria = UserFilterCriteria.builder()
				.login(login)
				.firstName(firstName)
				.lastName(lastName)
				.blocked(blocked)
				.dateBirthFrom(dateBirthFrom)
				.dateBirthTo(dateBirthTo)
				.dateRegisterFrom(dateRegisterFrom)
				.dateRegisterTo(dateRegisterTo)
				.gender(gender)
				.info(info)
				.country(country)
				.city(city)
				.minRating(minRating)
				.maxRating(maxRating)
				.role(Role.getRoleByName(roleName))
				.sortField(sortField)
				.sortDirection(sortDirection)
				.build();
		
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