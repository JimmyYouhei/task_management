package com.hvcg.api.task_management.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.hvcg.api.task_management.constant.Status;
import com.hvcg.api.task_management.model.ProjectInforWrapper;
import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.model.SubTaskInforWrapper;
import com.hvcg.api.task_management.model.TaskCategoryInforWrapper;

public class StaffReportRowMapper implements RowMapper<StaffReport> {

	@Override
	public StaffReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		StaffReport staffReport = new StaffReport();
		
		try {
		staffReport.setId(rs.getInt("id"));
		staffReport.setFullName(rs.getString("full_name"));
		staffReport.setDateOfBirth(rs.getDate("date_of_birth"));
		staffReport.setPhoneNumber(rs.getString("phone_number"));
		staffReport.setEmail(rs.getString("email"));
		staffReport.setFacebook(rs.getString("facebook"));
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}
		
		try {
			staffReport.setUsername(rs.getString("username"));
			staffReport.setRole(rs.getString("role"));
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}
		
		try {
			staffReport.setOfficeAddress(rs.getString("address"));
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}
		
		try {
			
			staffReport.getTeams().put(rs.getString("team_name"), rs.getString("team_description"));
			
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}

		try {
			
			staffReport.getProjectsParticipated().put(rs.getString("project_name"), rs.getString("project_description"));
			
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}
		
		
		try {
			SubTaskInforWrapper subtaskInfor = 
					new SubTaskInforWrapper(rs.getString("subtask_name") , rs.getString("subtask_description") , 
							rs.getDate("date_start") , rs.getDate("date_finish") , Status.valueOf(rs.getString("status")));
			

			
			List<SubTaskInforWrapper> subtasks = new ArrayList<>();
			subtasks.add(subtaskInfor);
			
			TaskCategoryInforWrapper taskCategoryInforWrapper = 
					new TaskCategoryInforWrapper(rs.getString("task_category_name") ,  rs.getString("task_category_description") , subtasks);
			
			
			List<TaskCategoryInforWrapper> taskCategories = new ArrayList<TaskCategoryInforWrapper>();
			
			taskCategories.add(taskCategoryInforWrapper);
			
			ProjectInforWrapper projectInforWrapper = new ProjectInforWrapper(rs.getString("project_name") , rs.getString("project_description") , taskCategories );
			
			List<ProjectInforWrapper> projWrappers = new ArrayList<>();
			
			projWrappers.add(projectInforWrapper);
			
			staffReport.setSubtasksDetail(projWrappers);
			
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}
		
		/*
		try {
			
			staffReport.getTaskCategories().put(rs.getString("task_category_name"), rs.getString("task_category_description"));
			
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}
		
		try {
			
			SubTaskInforWrapper subtaskInfor = 
					new SubTaskInforWrapper(rs.getString("subtask_description") , 
							rs.getDate("date_start") , rs.getDate("date_finish") , Status.valueOf(rs.getString("status")));
			
			staffReport.getSubtasks().put(rs.getString("subtask_name") , subtaskInfor);
			
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}
		
		*/
		
		return staffReport;
	}

}

