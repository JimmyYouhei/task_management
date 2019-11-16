package com.hocvoichuyengia.spring.task_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hocvoichuyengia.spring.task_management.entity.Team;
import com.hocvoichuyengia.spring.task_management.entity.TeamProject;

public interface TeamProjectRepository extends JpaRepository<TeamProject, Integer> {

	List<TeamProject> findByProjectId(int id);

}
