package com.hvcg.api.task_management.service;

import com.hvcg.api.task_management.model.StaffReport;

public interface StaffReportService {
	
	StaffReport getFullStaffReportById(int staffId);
	
	StaffReport getBasicStaffInforById(int staffId);
	
	StaffReport getStaffLoginDetailById(int staffId);

	StaffReport getStaffOfficeAddressById(int staffId);
	
	StaffReport getTeamsById(int staffId);
	
	StaffReport getProjectsById(int staffId);
	
	// StaffReport getTaskCategoriesById(int staffId);
	
	StaffReport getSubtaskById(int StaffId);
}
