package com.hvcg.api.task_management.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hvcg.api.task_management.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

	
}
