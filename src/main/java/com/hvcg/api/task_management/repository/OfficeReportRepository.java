package com.hvcg.api.task_management.repository;

import com.hvcg.api.task_management.model.OfficeReport;

public interface OfficeReportRepository {
	
	OfficeReport getOfficeReportById(int officeId);

}
