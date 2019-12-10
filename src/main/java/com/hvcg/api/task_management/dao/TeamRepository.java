package com.hvcg.api.task_management.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.Team;

/**
 * 
 * Spring data JpaRepository to quickly handle team data and with default Spring Data Rest API exposure
 * 
 * some method were added with extra Annotation to restricted to ADMIN role only
 * 
 * @author JY
 *
 */


public interface TeamRepository extends JpaRepository<Team, Integer> {

	List<Team> findByNameLike(String keyword);
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Integer id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void delete(Team entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll(Iterable<? extends Team> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll();

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteInBatch(Iterable<Team> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAllInBatch();

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Team> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Team> List<S> saveAll(Iterable<S> entities);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Team> S saveAndFlush(S entity);
	
}
