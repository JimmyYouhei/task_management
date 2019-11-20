package com.hvcg.api.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hvcg.api.task_management.entity.Staff;

@RepositoryRestResource(exported = false)
public interface InternalStaffRepository extends JpaRepository<Staff, Integer> {

}
