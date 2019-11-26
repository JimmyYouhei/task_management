package com.hvcg.api.task_management.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hvcg.api.task_management.model.TaskReportInforWrapper;


/**
 * 
 * RowMapper implement to use for Spring JDBC
 * 
 * @author JY
 *
 */

public class TaskReportRowMapper implements RowMapper<TaskReportInforWrapper>{

	@Override
	public TaskReportInforWrapper mapRow(ResultSet rs, int rowNum) throws SQLException {
		TaskReportInforWrapper taskReport = new TaskReportInforWrapper();
		
		try {
			
			taskReport.setNumberOfTasks(rs.getInt("total_task"));
			
		} catch (SQLException lackingColumn) {
			System.out.println(lackingColumn.getMessage());
		}
		
		return taskReport;
	}

}
