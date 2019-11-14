package com.hocvoichuyengia.spring.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hocvoichuyengia.spring.task_management.entity.Office;

public interface OfficeRepository extends JpaRepository<Office, Integer> {

	
}
