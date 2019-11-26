package com.hvcg.api.task_management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.Office;
import com.hvcg.api.task_management.entity.Staff;

public interface OfficeRepository extends JpaRepository<Office, Integer> {
	
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
