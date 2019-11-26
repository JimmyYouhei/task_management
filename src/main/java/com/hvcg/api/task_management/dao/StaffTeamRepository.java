package com.hvcg.api.task_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.StaffTeam;


/**
 * 
 * 
 * Spring data JpaRepository to quickly handle staff-team assignment data and with default Spring Data Rest API exposure
 * 
 * some method were added with extra Annotation to restricted to ADMIN role only
 * 
 * @author JY
 *
 */

public interface StaffTeamRepository extends JpaRepository<StaffTeam, Integer> {
	
	// Spring magic: the name of this method is not random
	List<StaffTeam> findByTeamId(int id);
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Integer id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void delete(StaffTeam entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll(Iterable<? extends StaffTeam> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll();

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteInBatch(Iterable<StaffTeam> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAllInBatch();

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends StaffTeam> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends StaffTeam> List<S> saveAll(Iterable<S> entities);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends StaffTeam> S saveAndFlush(S entity);
	
}
