package com.example.artspectrum.repositories;

import com.example.artspectrum.entities.User;
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
}
