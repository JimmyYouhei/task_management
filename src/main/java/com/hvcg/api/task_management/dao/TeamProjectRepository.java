package com.hvcg.api.task_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hvcg.api.task_management.entity.TeamProject;

public interface TeamProjectRepository extends JpaRepository<TeamProject, Integer> {

	List<TeamProject> findByProjectId(int id);

}
