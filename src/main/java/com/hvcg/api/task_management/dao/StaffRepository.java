package com.hvcg.api.task_management.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.Staff;

/**
 * 
 * Spring data JpaRepository to quickly handle staff data and with default Spring Data Rest API exposure
 * 
 * some method were added with extra Annotation to restricted to ADMIN role only
 * 
 * @author JY
 *
 */

public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
	List<Staff> findByFullNameLike(String name);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Integer id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void delete(Staff entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll(Iterable<? extends Staff> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll();

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteInBatch(Iterable<Staff> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAllInBatch();

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Staff> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Staff> List<S> saveAll(Iterable<S> entities);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Staff> S saveAndFlush(S entity);
	
}
