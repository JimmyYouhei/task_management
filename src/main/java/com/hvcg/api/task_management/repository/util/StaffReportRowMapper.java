package com.hvcg.api.task_management.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hvcg.api.task_management.model.StaffReport;

public class StaffReportRowMapper implements RowMapper<StaffReport> {

	@Override
	public StaffReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		StaffReport staffReport = new StaffReport();
		staffReport.setId(rs.getInt("id"));
		staffReport.setFullName(rs.getString("full_name"));
		staffReport.setDateOfBirth(rs.getDate("date_of_birth"));
		staffReport.setPhoneNumber(rs.getString("phone_number"));
		staffReport.setEmail(rs.getString("email"));
		staffReport.setFacebook(rs.getString("facebook"));
		staffReport.setUsername(rs.getString("username"));
		staffReport.setRole(rs.getString("role"));
		
		return staffReport;
	}

}

