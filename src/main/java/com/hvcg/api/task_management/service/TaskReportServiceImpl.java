package com.hvcg.api.task_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvcg.api.task_management.model.OfficeTaskReportInforWrapper;
import com.hvcg.api.task_management.model.TaskReportInforWrapper;
import com.hvcg.api.task_management.repository.TaskReportRepository;

@Service
public class TaskReportServiceImpl implements TaskReportService {

	@Autowired
	TaskReportRepository taskReportRepository;
	
	@Override
	public TaskReportInforWrapper getTotalTaskNumber() {
		
		return taskReportRepository.getTotalTask();
	}

	@Override
	public TaskReportInforWrapper getCalculateTaskTime() {
		return taskReportRepository.calculateTaskTime();
	}

	@Override
	public TaskReportInforWrapper getFullTaskReport() {
		return taskReportRepository.getFullReport();
	}

	@Override
	public TaskReportInforWrapper getFullTaskReportFromStaff(int staffId) {

		return taskReportRepository.getReportFromStaff(staffId);
		
	}

	@Override
	public TaskReportInforWrapper getTotalTaskNumberReportFromStaff(int staffId) {
		
		return taskReportRepository.getTotalTaskNumberReportFromStaff(staffId);
	}

	@Override
	public TaskReportInforWrapper getTimeReportFromStaff(int staffId) {

		return taskReportRepository.getTimeReportFromStaff(staffId);
	}

	@Override
	public OfficeTaskReportInforWrapper getOfficeTaskReport(int officeId) {
		return taskReportRepository.getOfficeTaskReport(officeId);
	}

}
