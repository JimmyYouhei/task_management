package com.hvcg.api.task_management.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hvcg.api.task_management.constant.Status;
import com.hvcg.api.task_management.model.TaskRowInforWrapper;

public class TaskDetailRowMapper implements RowMapper<TaskRowInforWrapper>{

	@Override
	public TaskRowInforWrapper mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TaskRowInforWrapper aTaskRow = new TaskRowInforWrapper();
		
		aTaskRow.setTaskId(rs.getInt("subtask_id"));
		aTaskRow.setDateStart(rs.getDate("date_start"));
		aTaskRow.setDateFinish(rs.getDate("date_finish"));
		aTaskRow.setStatus(Status.valueOf(rs.getString("status")));
		
		return aTaskRow;
	}
	
	

}
