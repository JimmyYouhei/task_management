package com.hvcg.api.task_management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.Office;
import com.hvcg.api.task_management.entity.Staff;

/**
 * 
 * Spring data JpaRepository to quickly handle office data and with default Spring Data Rest API exposure
 * 
 * some method were added with extra Annotation to restricted to ADMIN role only
 * 
 * @author JY
 *
 */

public interface OfficeRepository extends JpaRepository<Office, Integer> {
	
	List<Office> findByAddressLike(String keyword);
	
	// Spring magic: the name of this method is not random
	Optional<Staff> findByPersonInChargeId(int id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Integer id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void delete(Office entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll(Iterable<? extends Office> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll();

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteInBatch(Iterable<Office> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAllInBatch();

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Office> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Office> List<S> saveAll(Iterable<S> entities);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Office> S saveAndFlush(S entity);
	
	
}
