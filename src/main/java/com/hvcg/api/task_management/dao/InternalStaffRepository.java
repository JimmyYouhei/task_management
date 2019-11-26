package com.hvcg.api.task_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hvcg.api.task_management.entity.InternalStaff;

/**
 * Spring data JpaRepository to quickly handle staff data
 * 
 * this interface included exported = false to hide this API from exposure
 * 
 * this is intended to handle restricted method and not to conflict with StaffRepository Interface
 * 
 * @author JY
 *
 */

@RepositoryRestResource(exported = false)
public interface InternalStaffRepository extends JpaRepository<InternalStaff, Integer> {

}
