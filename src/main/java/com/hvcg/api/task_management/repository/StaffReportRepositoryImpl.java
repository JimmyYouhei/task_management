package com.hvcg.api.task_management.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hvcg.api.task_management.model.ProjectInforWrapper;
import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.model.SubTaskInforWrapper;
import com.hvcg.api.task_management.model.TaskCategoryInforWrapper;
import com.hvcg.api.task_management.repository.util.StaffReportRowMapper;

/**
 * 
 * Staff Report Repository implementation to generate Staff Report
 * 
 * The full report is generated through many step to avoid missing information make the whole report null
 *
 * 
 * @author JY
 *
 */

@Repository
public class StaffReportRepositoryImpl implements StaffReportRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public StaffReport getFullStaffReportById(int staffId) {
		StaffReport result = new StaffReport();
		try {
		result = getStaffBasicInforById(staffId);
		}catch (EmptyResultDataAccessException noData) {
			System.out.println(noData.getMessage());
		}
		
		
		try {
		StaffReport loginDetail = getStaffLoginDetailById(staffId);
		
		result.setUsername(loginDetail.getUsername());
		result.setRole(loginDetail.getRole());
		} catch (EmptyResultDataAccessException noData) {
			System.out.println(noData.getMessage());
		}

		
		try {
		StaffReport officeAddress = getStaffOfficeAddressById(staffId);
		
		result.setOfficeAddress(officeAddress.getOfficeAddress());
		} catch (EmptyResultDataAccessException noData) {
			System.out.println(noData.getMessage());
		}
		
		
		try {
		StaffReport teams = getTeamsById(staffId);
		
		result.setTeams(teams.getTeams());
		}catch (EmptyResultDataAccessException noData) {
			System.out.println(noData.getMessage());
		}
		
		try {
		StaffReport projectParticipated = getProjectsById(staffId);
		
		result.setProjectsParticipated(projectParticipated.getProjectsParticipated());
		} catch (EmptyResultDataAccessException noData) {
			System.out.println(noData.getMessage());
		}
		
		
		try {
		StaffReport subtaskDetail = getSubtaskById(staffId);
		
		result.setSubtasksDetail(subtaskDetail.getSubtasksDetail());
		} catch (EmptyResultDataAccessException noData) {
			System.out.println(noData.getMessage());
		}
		
		return result;
	}

	@Override
	public StaffReport getStaffBasicInforById(int staffId) {
		StaffReport staffReport = jdbcTemplate.queryForObject( "SELECT id , full_name , date_of_birth , phone_number , email , facebook" + 
				" FROM staff" +
				" WHERE id = ? " , new StaffReportRowMapper() ,staffId );
		return staffReport;
	}

	@Override
	public StaffReport getStaffLoginDetailById(int staffId) {
		StaffReport staffReport = jdbcTemplate.queryForObject("SELECT se.username , se.role" + 
				" FROM staff AS st" +
				" INNER JOIN security AS se" +
				" ON st.id = se.staff_id" +
				" WHERE st.id = ?" , new StaffReportRowMapper() , staffId );
		
		return staffReport;
	}

	@Override
	public StaffReport getStaffOfficeAddressById(int staffId) {
		
		StaffReport staffReport = jdbcTemplate.queryForObject("SELECT o.address" + 
				" FROM staff AS st" +
				" INNER JOIN office AS o" +
				" ON st.office_id = o.id" +
				" WHERE st.id = ?" , new StaffReportRowMapper() , staffId);
		
		return staffReport;
	}

	@Override
	public StaffReport getTeamsById(int staffId) {

		List<StaffReport> staffReport = jdbcTemplate.query("SELECT t.name AS team_name ," + 
				" t.description AS team_description" +
				" FROM staff AS st" +
				" INNER JOIN staff_team AS stt" +
				" ON st.id = stt.staff_id" +
				" INNER JOIN team AS t" +
				" ON t.id = stt.team_id" +
				" WHERE st.id = ?" , new StaffReportRowMapper() , staffId);
		
		if(staffReport.size() < 1) {
			return new StaffReport();
		}
		
		StaffReport result = staffReport.get(0);
		
		if(staffReport.size()>1) {
			
			for(int i = 1 ; i < staffReport.size() ; i++ ) {
				
				result.getTeams().put(staffReport.get(i).getTeams().get(0), 
						staffReport.get(i).getTeams().getValue(0));
				
			}
		}
		
		return result;
	}

	@Override
	public StaffReport getProjectsById(int staffId) {
		
		List<StaffReport> staffReport = jdbcTemplate.query("SELECT p.name AS project_name , p.description AS project_description" +
				" FROM staff AS st" +
				" INNER JOIN staff_team AS stt" +
				" ON st.id = stt.staff_id" +
				" INNER JOIN team AS t" +
				" ON t.id = stt.team_id" +
				" INNER JOIN team_project AS tp" +
				" ON t.id = tp.team_id" +
				" INNER JOIN project AS p" +
				" ON tp.project_id = p.id" +
				" WHERE st.id = ?" , new StaffReportRowMapper() , staffId);
		
		if(staffReport.size() < 1) {
			return new StaffReport();
		}
		
		StaffReport result = staffReport.get(0);
		
		if(staffReport.size() > 1) {
			
			for(int i = 1 ; i < staffReport.size() ; i++) {
				
				result.getProjectsParticipated().put(staffReport.get(i).getProjectsParticipated().get(0) , 
						staffReport.get(i).getProjectsParticipated().getValue(0));
			}
			
		}
		
		return result;
	}
	
	
	@Override
	public StaffReport getSubtaskById(int staffId) {
		
		List<StaffReport> staffReports = jdbcTemplate.query(
				"SELECT su.name AS subtask_name , su.description AS subtask_description," +
				" su.date_start , su.date_finish , su.status ," + 
				" tc.name AS task_category_name , tc.description AS task_category_description," +
				" p.name AS project_name , p.description AS project_description" +
				" FROM staff AS st" +
				" INNER JOIN staff_subtask AS stsu" +
				" ON st.id = stsu.staff_id" +
				" INNER JOIN subtask AS su" +
				" ON stsu.subtask_id = su.id" +
				" INNER JOIN task_category AS tc" +
				" ON tc.id = su.task_category_id" +
				" INNER JOIN project AS p" + 
				" ON p.id = tc.project_id" +
				" WHERE st.id = ?" , new StaffReportRowMapper() , staffId);
		
		if(staffReports.size() < 1) {
			return new StaffReport();
		}
		
		StaffReport result = staffReports.get(0);
		
		if(staffReports.size() > 1) {
			
			for (int i = 1 ; i < staffReports.size() ; i++) {
				mergeSubtaskToStaffReportFromRow(result, staffReports.get(i));
			}
			
		}
		
		return result;
	}

	private void mergeSubtaskToStaffReportFromRow(StaffReport result, StaffReport staffReport) {
		ProjectInforWrapper projectToMerge = staffReport.getSubtasksDetail().get(0);
		
		TaskCategoryInforWrapper taskCategoryToMerge = staffReport.getSubtasksDetail().get(0).getTaskCategories().get(0);
		
		SubTaskInforWrapper subtaskToMerge = staffReport.getSubtasksDetail().get(0).getTaskCategories().get(0).getSubtasks().get(0);
		
		if(!result.containProjectName(projectToMerge)){
			result.getSubtasksDetail().add(projectToMerge);
			return;
		} else {
			
			int projectPosition = result.positionOfProject(projectToMerge);
			
			ProjectInforWrapper projectPointer = result.getSubtasksDetail().get(projectPosition);
			
			if(! projectPointer.containTaskCategoryName(taskCategoryToMerge)) {
				projectPointer.getTaskCategories().add(taskCategoryToMerge);
				return;
			} else {
				
				int taskCategoryPosition = projectPointer.positionOfTaskCategory(taskCategoryToMerge);
				
				TaskCategoryInforWrapper taskCategoryPointer = projectPointer.getTaskCategories().get(taskCategoryPosition);
				
				if(! taskCategoryPointer.containSubtaskCategoryName(subtaskToMerge)) {
					taskCategoryPointer.getSubtasks().add(subtaskToMerge);
				}
				
			}
			
		}
		
		
	}
	
}
