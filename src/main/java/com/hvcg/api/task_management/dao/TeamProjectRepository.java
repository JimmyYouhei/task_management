package com.hvcg.api.task_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.TeamProject;

/**
 * 
 * Spring data JpaRepository to quickly handle team - project assignment data and with default Spring Data Rest API exposure
 * 
 * some method were added with extra Annotation to restricted to ADMIN role only
 * 
 * 
 * @author JY
 *
 */


public interface TeamProjectRepository extends JpaRepository<TeamProject, Integer> {

	// Spring magic: the name of this method is not random
	List<TeamProject> findByProjectId(int id);

	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Integer id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void delete(TeamProject entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll(Iterable<? extends TeamProject> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll();

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteInBatch(Iterable<TeamProject> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAllInBatch();

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends TeamProject> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends TeamProject> List<S> saveAll(Iterable<S> entities);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends TeamProject> S saveAndFlush(S entity);
	
}
