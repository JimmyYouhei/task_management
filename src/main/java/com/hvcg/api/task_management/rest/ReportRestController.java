package com.hvcg.api.task_management.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.repository.util.StaffReportRowMapper;
import com.hvcg.api.task_management.service.StaffReportService;

@RestController
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	private StaffReportService staffReportService;
	
	
	@GetMapping("/staff/{staffId}")
	public StaffReport getStaffReport(@PathVariable int staffId) {
		
		return staffReportService.getFullStaffReportById(staffId);
	}

	
}
