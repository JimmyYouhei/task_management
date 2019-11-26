package com.hvcg.api.task_management.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hvcg.api.task_management.entity.User;

/**
 * 
 * Spring data JpaRepository to quickly handle security data
 * 
 * this interface included exported = false to hide this API from exposure
 * 
 * 
 * @author JY
 *
 */

@RepositoryRestResource(exported = false)
public interface SecurityRepository extends JpaRepository<User, Integer> {
	
	// Spring magic: the name of this method is not random
	Optional<User> findByUsername(String username);

	
}
