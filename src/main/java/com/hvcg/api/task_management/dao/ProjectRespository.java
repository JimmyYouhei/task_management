package com.hvcg.api.task_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.Project;

/**
 * 
 * Spring data JpaRepository to quickly handle project data and with default Spring Data Rest API exposure
 * 
 * some method were added with extra Annotation to restricted to ADMIN role only
 * 
 * @author JY
 *
 */

public interface ProjectRespository extends JpaRepository<Project, Integer> {
	
	
	List<Project> findByNameLike(String keyword);
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Integer id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void delete(Project entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll(Iterable<? extends Project> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll();

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteInBatch(Iterable<Project> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAllInBatch();

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Project> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Project> List<S> saveAll(Iterable<S> entities);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends Project> S saveAndFlush(S entity);

}
