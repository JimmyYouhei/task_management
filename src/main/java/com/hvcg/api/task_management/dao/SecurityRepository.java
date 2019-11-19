package com.hvcg.api.task_management.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hvcg.api.task_management.entity.User;

public interface SecurityRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
}
