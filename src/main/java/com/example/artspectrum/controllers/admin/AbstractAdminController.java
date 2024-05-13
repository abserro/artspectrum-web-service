package com.example.artspectrum.controllers.admin;

import com.example.artspectrum.services.admin.IAdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/artspectrum/admin")
public abstract class AbstractAdminController<T, D> {
	protected final IAdminService<T, D> adminService;
	
	public AbstractAdminController(IAdminService<T, D> adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<T>> getAll(@RequestParam(required = false) Specification<T> specification,
	                                      @RequestParam(defaultValue = "0") int page,
	                                      @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<T> entities = adminService.getAll(specification, pageable);
		return ResponseEntity.ok(entities);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable Long id) {
		T entity = adminService.getById(id);
		return ResponseEntity.ok(entity);
	}
	
	@PostMapping
	public ResponseEntity<T> create(@RequestBody T entity) {
		T createdEntity = adminService.create(entity);
		return ResponseEntity.ok(createdEntity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<T> update(@PathVariable Long id, @RequestBody D dto) {
		T updatedEntity = adminService.update(id, dto);
		return ResponseEntity.ok(updatedEntity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		boolean deleted = adminService.delete(id);
		return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<Void> deleteAll() {
		adminService.deleteAll();
		return ResponseEntity.ok().build();
	}
}
