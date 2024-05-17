package com.example.artspectrum.repositories;

import com.example.artspectrum.entities.User;
import com.example.artspectrum.utils.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	Optional<User> findByLogin(String login);
	boolean existsByLogin(String username);
	Page<User> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);
	Page<User> findByRoles(Role role, Pageable unpaged);
	Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String keyword, String keyword1, Pageable pageable);
	Page<User> findByCountryIgnoreCase(String country, Pageable pageable);
	Page<User> findByCityIgnoreCase(String city, Pageable pageable);
	Page<User> findByTotalRatingBetween(double minRating, double maxRating, Pageable pageable);
	Page<User> findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndCountryIgnoreCase(String firstName, String lastName, String country, Pageable pageable);
	Page<User> findByInfoContainingIgnoreCase(String keyword, Pageable pageable);
//	Page<User> findAllWith(Pageable pageable);
}
