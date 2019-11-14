package com.hocvoichuyengia.spring.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hocvoichuyengia.spring.task_management.entity.Project;

public interface ProjectRespository extends JpaRepository<Project, Integer> {

}
