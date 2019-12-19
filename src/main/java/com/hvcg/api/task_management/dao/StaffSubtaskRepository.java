package com.hvcg.api.task_management.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hvcg.api.task_management.entity.StaffSubtask;


/**
 * 
 * Spring data JpaRepository to quickly handle staff-subtask assignment data and with default Spring Data Rest API exposure
 * 
 * some method were added with extra Annotation to restricted to ADMIN role only
 * 
 * @author JY
 *
 */

public interface StaffSubtaskRepository extends JpaRepository<StaffSubtask, Integer> {

	@Query("select s from StaffSubtask s where s.staff.id = :staffId " +
			"and month(s.createTime) = :month and year(s.createTime) = :year")
	List<StaffSubtask> queryByStaffIdAndMonth(@Param("staffId") int staffId ,
											  @Param("month") int month,
											  @Param("year") int year);

	@Query("select s from StaffSubtask s where s.staff.id = :staffId " +
			"and month(s.subtask.dateFinish) = :month and year(s.subtask.dateFinish) = :year " +
			"and s.subtask.status = 'FINISHED' ")
	List<StaffSubtask> queryFinishByStaffIdAndMonth(@Param("staffId") int staffId,
													@Param("month") int month,
													@Param("year") int year);
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteById(Integer id);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void delete(StaffSubtask entity);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll(Iterable<? extends StaffSubtask> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAll();

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteInBatch(Iterable<StaffSubtask> entities);

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	void deleteAllInBatch();

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends StaffSubtask> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends StaffSubtask> List<S> saveAll(Iterable<S> entities);

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	<S extends StaffSubtask> S saveAndFlush(S entity);
	
	
	
}
