package com.hvcg.api.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hvcg.api.task_management.entity.Subtask;

public interface SubtaskRepository extends JpaRepository<Subtask, Integer> {

}
