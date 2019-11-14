package com.hocvoichuyengia.spring.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hocvoichuyengia.spring.task_management.entity.Subtask;

public interface SubtaskRepository extends JpaRepository<Subtask, Integer> {

}
