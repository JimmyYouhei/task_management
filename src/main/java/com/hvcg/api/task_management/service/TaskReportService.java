package com.hvcg.api.task_management.service;

import com.hvcg.api.task_management.model.OfficeTaskReportInforWrapper;
import com.hvcg.api.task_management.model.TaskReportInforWrapper;

public interface TaskReportService {
	
	TaskReportInforWrapper getTotalTaskNumber();
	
	TaskReportInforWrapper getCalculateTaskTime();
	
	TaskReportInforWrapper getFullTaskReport();
	
	TaskReportInforWrapper getFullTaskReportFromStaff(int staffId);
	
	TaskReportInforWrapper getTotalTaskNumberReportFromStaff(int staffId);
	
	TaskReportInforWrapper getTimeReportFromStaff(int staffId);
	
	OfficeTaskReportInforWrapper getOfficeTaskReport(int officeId);
}
