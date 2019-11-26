package com.hvcg.api.task_management.repository;

import com.hvcg.api.task_management.model.OfficeReport;

/**
 * 
 * repository to get office report
 * 
 * @author JY
 *
 */

public interface OfficeReportRepository {
	
	OfficeReport getOfficeReportById(int officeId);

}
