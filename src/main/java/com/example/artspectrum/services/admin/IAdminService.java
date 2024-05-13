package com.example.artspectrum.services.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IAdminService<T, D> {
	Page<T> getAll(Specification<T> specification, Pageable pageable);
	T getById(Long id);
	T create(T entity);
	T update(Long id, D dto);
	boolean delete(Long id);
	void deleteAll();
	
}
