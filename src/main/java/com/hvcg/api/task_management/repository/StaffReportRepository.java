package com.hvcg.api.task_management.repository;

import com.hvcg.api.task_management.model.StaffReport;

public interface StaffReportRepository {

	StaffReport getFullStaffReportById(int staffId);
	
	StaffReport getStaffBasicInforById(int staffId);
	
	StaffReport getStaffLoginDetailById(int staffId);
	
	StaffReport getStaffOfficeAddressById(int staffId);
	
	StaffReport getTeamsById(int staffId);
	
	StaffReport getProjectsById(int staffId);
	
	StaffReport getSubtaskById(int staffId);
	
}
