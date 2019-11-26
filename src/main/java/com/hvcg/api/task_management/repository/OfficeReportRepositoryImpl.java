package com.hvcg.api.task_management.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hvcg.api.task_management.dao.InternalStaffRepository;
import com.hvcg.api.task_management.model.OfficeReport;
import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.repository.util.OfficeReportRowMapper;
import com.hvcg.api.task_management.service.StaffReportService;

@Repository
public class OfficeReportRepositoryImpl implements OfficeReportRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	InternalStaffRepository internalStaffRepository;
	
	@Autowired
	StaffReportService staffReportService;
	
	@Override
	public OfficeReport getOfficeReportById(int officeId) {
		
		List<OfficeReport> officeReports = jdbcTemplate.query(
				"SELECT o.id , o.address , o.person_in_charge_id , st.id AS staff_id" +
				" FROM office AS o" +
				" INNER JOIN staff as st" +
				" ON st.office_id = o.id" +
				" WHERE o.id = ?" , new OfficeReportRowMapper(internalStaffRepository , staffReportService ) , officeId);
		
		if(officeReports.size() < 1) {
			return new OfficeReport();
		}
		
		OfficeReport result = officeReports.get(0);
		
		if(officeReports.size() > 1 ) {
			
			for(int i = 1 ; i < officeReports.size() ; i++) {
				StaffReport staffReportToAdd = officeReports.get(i).getStaffs().get(0);
				
				if(!result.containStaffReport(staffReportToAdd)) {
					result.getStaffs().add(staffReportToAdd);
				}
				
			}
			
		}
		
		return result;
	}
	
	

}
