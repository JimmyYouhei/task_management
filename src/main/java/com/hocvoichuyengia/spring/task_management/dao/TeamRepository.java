package com.hocvoichuyengia.spring.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hocvoichuyengia.spring.task_management.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
