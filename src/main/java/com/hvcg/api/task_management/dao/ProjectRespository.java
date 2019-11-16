package com.hvcg.api.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hvcg.api.task_management.entity.Project;

public interface ProjectRespository extends JpaRepository<Project, Integer> {

}
