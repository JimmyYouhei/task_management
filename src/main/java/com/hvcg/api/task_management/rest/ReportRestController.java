package com.hvcg.api.task_management.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.model.OfficeReport;
import com.hvcg.api.task_management.model.OfficeTaskReportInforWrapper;
import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.model.TaskReportInforWrapper;
import com.hvcg.api.task_management.service.OfficeReportService;
import com.hvcg.api.task_management.service.StaffReportService;
import com.hvcg.api.task_management.service.TaskReportService;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	private StaffReportService staffReportService;
	
	@Autowired
	private OfficeReportService officeReportService;
	
	@Autowired
	private TaskReportService taskReportService;
	
	@GetMapping("/staff/{staffId}")
	public StaffReport getStaffReportById(@PathVariable int staffId) {
		
		return staffReportService.getFullStaffReportById(staffId);
	}
	
	
	@GetMapping("/office/{officeId}")
	public OfficeReport getOfficeReportById(@PathVariable int officeId) {
		return officeReportService.getOfficeReportByOfficeId(officeId);
	}

	@GetMapping("/task")
	public TaskReportInforWrapper getAllTasksReport() {
		return taskReportService.getFullTaskReport();
	}
	
	@GetMapping("/task/staff/{staffId}")
	public TaskReportInforWrapper getFullTaskReportFromStaff(@PathVariable int staffId) {
		
		return taskReportService.getFullTaskReportFromStaff(staffId);
		
	}
	
	@GetMapping("/task/office/{officeId}")
	public OfficeTaskReportInforWrapper getOfficeTaskReport(@PathVariable int officeId) {
		return taskReportService.getOfficeTaskReport(officeId);
	}
	
}
