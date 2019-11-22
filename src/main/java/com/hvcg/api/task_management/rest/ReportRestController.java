package com.hvcg.api.task_management.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.repository.util.StaffReportRowMapper;

@RestController
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/staff/{staffId}")
	public StaffReport getStaffReport(@PathVariable int staffId) {
		
		StaffReport staffReport = jdbcTemplate.queryForObject("SELECT st.id , st.full_name , st.date_of_birth , st.phone_number , st.email , st.facebook , se.username , se.role"
				+ "  FROM staff AS st"
				+ "  INNER JOIN security as se"
				+ "  ON st.id = se.staff_id"
				+ "  WHERE st.id =?", new StaffReportRowMapper() , staffId);
		
		return staffReport;
	}
	
}
