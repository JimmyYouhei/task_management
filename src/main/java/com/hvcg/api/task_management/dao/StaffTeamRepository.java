package com.hvcg.api.task_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hvcg.api.task_management.entity.StaffTeam;

public interface StaffTeamRepository extends JpaRepository<StaffTeam, Integer> {
	
	List<StaffTeam> findByTeamId(int id);
	
}
