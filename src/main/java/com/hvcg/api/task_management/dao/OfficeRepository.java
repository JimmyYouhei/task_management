package com.hvcg.api.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hvcg.api.task_management.entity.Office;

public interface OfficeRepository extends JpaRepository<Office, Integer> {

	
}
