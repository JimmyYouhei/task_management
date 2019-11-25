package com.hvcg.api.task_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.repository.StaffReportRepository;

@Service
public class StaffReportServiceImpl implements StaffReportService {

	@Autowired
	private StaffReportRepository staffReportRepository; 
	
	@Override
	public StaffReport getFullStaffReportById(int staffId) {
		
		return staffReportRepository.getFullStaffReportById(staffId);
		
	}

	@Override
	public StaffReport getBasicStaffInforById(int staffId) {
		return staffReportRepository.getStaffBasicInforById(staffId);
	}

	@Override
	public StaffReport getStaffLoginDetailById(int staffId) {
		return staffReportRepository.getStaffLoginDetailById(staffId);
	}

	@Override
	public StaffReport getStaffOfficeAddressById(int staffId) {

		return staffReportRepository.getStaffOfficeAddressById(staffId);
		
	}

	@Override
	public StaffReport getTeamsById(int staffId) {
		return staffReportRepository.getTeamsById(staffId);
	}

	@Override
	public StaffReport getProjectsById(int staffId) {
		return staffReportRepository.getProjectsById(staffId);
	}
 
	/*
	@Override
	public StaffReport getTaskCategoriesById(int staffId) {

		return staffReportRepository.getTaskCategoriesById(staffId);
	}
	*/

	@Override
	public StaffReport getSubtaskById(int staffId) {
		return staffReportRepository.getSubtaskById(staffId);
	}
	
	
	

}
