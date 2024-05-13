package com.example.artspectrum.utils;

import com.example.artspectrum.entities.User;
import com.example.artspectrum.utils.UserFilterCriteria;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {
	
	public static Specification<User> buildFilterCriteria(UserFilterCriteria criteria) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (criteria.getLogin() != null) {
				predicates.add(criteriaBuilder.equal(root.get("login"), criteria.getLogin()));
			}
			if (criteria.getFirstName() != null) {
				predicates.add(criteriaBuilder.equal(root.get("firstName"), criteria.getFirstName()));
			}
			if (criteria.getLastName() != null) {
				predicates.add(criteriaBuilder.equal(root.get("lastName"), criteria.getLastName()));
			}
			if (criteria.getBlocked() != null) {
				predicates.add(criteriaBuilder.equal(root.get("blocked"), criteria.getBlocked()));
			}
			if (criteria.getDateBirthFrom() != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateBirth"), criteria.getDateBirthFrom()));
			}
			if (criteria.getDateBirthTo() != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateBirth"), criteria.getDateBirthTo()));
			}
			if (criteria.getDateRegisterFrom() != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("registered"), criteria.getDateRegisterFrom()));
			}
			if (criteria.getDateRegisterTo() != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("registered"), criteria.getDateRegisterTo()));
			}
			if (criteria.getGender() != null) {
				predicates.add(criteriaBuilder.equal(root.get("gender"), criteria.getGender()));
			}
			if (criteria.getInfo() != null && !criteria.getInfo().isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("info"), criteria.getInfo()));
			}
			if (criteria.getCountry() != null && !criteria.getCountry().isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("country"), criteria.getCountry()));
			}
			if (criteria.getCity() != null && !criteria.getCity().isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("city"), criteria.getCity()));
			}
			if (criteria.getMinRating() != null && criteria.getMaxRating() != null) {
				predicates.add(criteriaBuilder.between(root.get("totalRating"), criteria.getMinRating(), criteria.getMaxRating()));
			}
			if (criteria.getRole() != null) {
				predicates.add(criteriaBuilder.isMember(criteria.getRole(), root.get("roles")));
			}
			// Сортировка
			if (criteria.getSortField() != null && !criteria.getSortField().isEmpty()) {
				query.orderBy(criteria.getSortDirection().equalsIgnoreCase("asc") ?
						criteriaBuilder.asc(root.get(criteria.getSortField())) :
						criteriaBuilder.desc(root.get(criteria.getSortField())));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}



