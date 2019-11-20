package com.hvcg.api.task_management.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hvcg.api.task_management.entity.User;

@RepositoryRestResource(exported = false)
public interface SecurityRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

	
}
