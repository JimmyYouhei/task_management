package com.hvcg.api.task_management.repository;

import com.hvcg.api.task_management.model.OfficeTaskReportInforWrapper;
import com.hvcg.api.task_management.model.TaskReportInforWrapper;

public interface TaskReportRepository {
	
	TaskReportInforWrapper getTotalTask();
	
	TaskReportInforWrapper calculateTaskTime();
	
	TaskReportInforWrapper getFullReport();
	
	TaskReportInforWrapper getReportFromStaff(int staffId);
	
	TaskReportInforWrapper getTotalTaskNumberReportFromStaff(int staffId);
	
	TaskReportInforWrapper getTimeReportFromStaff(int staffId);
	
	OfficeTaskReportInforWrapper getOfficeTaskReport(int officeId);

}
