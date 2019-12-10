package com.hvcg.api.task_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.TaskCategory;

/**
 * 
 * Spring data JpaRepository to quickly handle Task Category data and with default Spring Data Rest API exposure
 * 
 * some method were added with extra Annotation to restricted to ADMIN role only
 * 
 * @author JY
 *
 */


public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Integer> {
	
	List<TaskCategory> findByNameLike(String keyword);
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Integer id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void delete(TaskCategory entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll(Iterable<? extends TaskCategory> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll();

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteInBatch(Iterable<TaskCategory> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAllInBatch();

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends TaskCategory> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends TaskCategory> List<S> saveAll(Iterable<S> entities);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends TaskCategory> S saveAndFlush(S entity);
	
}
