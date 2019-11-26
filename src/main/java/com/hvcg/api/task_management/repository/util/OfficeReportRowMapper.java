package com.hvcg.api.task_management.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hvcg.api.task_management.dao.InternalStaffRepository;
import com.hvcg.api.task_management.entity.InternalStaff;
import com.hvcg.api.task_management.model.OfficeReport;
import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.service.StaffReportService;

/**
 * 
 * RowMapper implement to use for Spring JDBC
 * 
 * @author JY
 *
 */

public class OfficeReportRowMapper implements RowMapper<OfficeReport> {

	InternalStaffRepository internalStaffRepository;
	
	StaffReportService staffReportService;
	
	
	public OfficeReportRowMapper(InternalStaffRepository internalStaffRepository,
			StaffReportService staffReportService) {
		super();
		this.internalStaffRepository = internalStaffRepository;
		this.staffReportService = staffReportService;
	}



	@Override
	public OfficeReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		OfficeReport officeReport = new OfficeReport();
		
		try {
		officeReport.setId(rs.getInt("id"));
		officeReport.setOfficeAddress(rs.getString("address"));
		int personInChargeId = rs.getInt("person_in_charge_id");
		InternalStaff personInCharge = internalStaffRepository.findById(personInChargeId)
				.orElse(new InternalStaff());
		officeReport.setPersonInCharge(personInCharge);
	
		int staffId = rs.getInt("staff_id");
		StaffReport staffReport = staffReportService.getFullStaffReportById(staffId);
		officeReport.getStaffs().add(staffReport);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return officeReport;
		
		
	}
	
}

