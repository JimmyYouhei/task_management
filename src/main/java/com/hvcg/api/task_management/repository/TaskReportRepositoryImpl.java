package com.hvcg.api.task_management.repository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hvcg.api.task_management.dao.InternalStaffRepository;
import com.hvcg.api.task_management.dao.OfficeRepository;
import com.hvcg.api.task_management.model.OfficeTaskReportInforWrapper;
import com.hvcg.api.task_management.model.TaskReportInforWrapper;
import com.hvcg.api.task_management.model.TaskRowInforWrapper;
import com.hvcg.api.task_management.repository.util.ListStaffIdRowMapper;
import com.hvcg.api.task_management.repository.util.TaskDetailRowMapper;
import com.hvcg.api.task_management.repository.util.TaskReportRowMapper;

@Repository
public class TaskReportRepositoryImpl implements TaskReportRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	OfficeRepository officeRepository;
	
	@Autowired 
	InternalStaffRepository internalStaffRepository;
	
	@Override
	public TaskReportInforWrapper getTotalTask() {

		TaskReportInforWrapper taskReport = jdbcTemplate.queryForObject(
				"SELECT COUNT(DISTINCT id) AS total_task" +
				" FROM subtask" , new TaskReportRowMapper());
		
		return taskReport;
		
	}

	@Override
	public TaskReportInforWrapper calculateTaskTime() {
		
		List<TaskRowInforWrapper> taskRows = jdbcTemplate.query("SELECT DISTINCT id AS subtask_id , date_start ," + 
				" date_finish , status FROM subtask" , new TaskDetailRowMapper());
		
		TaskReportInforWrapper taskReport = new TaskReportInforWrapper();
		
		if(taskRows.isEmpty()) {
			return taskReport;
		}
		
		long totalEstimateTime = calculateTotalEstimateTime(taskRows);
		
		long totalTimeSpent = calculateTotalTimeSpent(taskRows);
		
		String timeSpent = convertFromMilisToString(totalTimeSpent);
		
		String estimateTime = convertFromMilisToString(totalEstimateTime);
		
		taskReport.setTotalEstimateTime(estimateTime);
		
		taskReport.setTotalTimeSpent(timeSpent);
		
		return taskReport;
	}
	
	@Override
	public TaskReportInforWrapper getFullReport() {
		TaskReportInforWrapper reportResult = new TaskReportInforWrapper();
		
		TaskReportInforWrapper totalTasks = getTotalTask();
		TaskReportInforWrapper timeReport = calculateTaskTime();
		
		reportResult.setNumberOfTasks(totalTasks.getNumberOfTasks());
		reportResult.setTotalEstimateTime(timeReport.getTotalEstimateTime());
		reportResult.setTotalTimeSpent(timeReport.getTotalTimeSpent());
		
		return reportResult;
	}


	@Override
	public TaskReportInforWrapper getReportFromStaff(int staffId) {
		TaskReportInforWrapper resultReport = getTotalTaskNumberReportFromStaff(staffId);
		
		TaskReportInforWrapper timeReport = getTimeReportFromStaff(staffId);
		
		resultReport.setTotalTimeSpent(timeReport.getTotalTimeSpent());
		resultReport.setTotalEstimateTime(timeReport.getTotalEstimateTime());
		
		return resultReport;
		
	}

	@Override
	public TaskReportInforWrapper getTotalTaskNumberReportFromStaff(int staffId) {
		TaskReportInforWrapper resultReport = jdbcTemplate.queryForObject(" SELECT COUNT(su.id) AS total_task" +
				" FROM staff AS st" + 
				" INNER JOIN staff_subtask AS stsu" +
				" ON st.id = stsu.staff_id" +
				" INNER JOIN subtask AS su" +
				" ON stsu.subtask_id = su.id" +
				" WHERE st.id = ?" , new TaskReportRowMapper() , staffId);
		
		return resultReport;
	}

	@Override
	public TaskReportInforWrapper getTimeReportFromStaff(int staffId) {
		TaskReportInforWrapper resultReport = new TaskReportInforWrapper();
		
		List<TaskRowInforWrapper> taskRows = jdbcTemplate.query("SELECT su.id AS subtask_id , su.date_start , su.date_finish , su.status" +
			" FROM staff AS st" + 
			" INNER JOIN staff_subtask AS stsu" +
			" ON st.id = stsu.staff_id" +
			" INNER JOIN subtask AS su" +
			" ON stsu.subtask_id = su.id" +
			" WHERE st.id = ?" , new TaskDetailRowMapper() , staffId);
		
		if(taskRows.isEmpty()) {
			return resultReport;
		}
		
		long totalEstimateTime = calculateTotalEstimateTime(taskRows);
		
		long totalTimeSpent = calculateTotalTimeSpent(taskRows);
		
		String totalEstimateTimeString = convertFromMilisToString(totalEstimateTime);
		
		String totalTimeSpentString = convertFromMilisToString(totalTimeSpent);
		
		resultReport.setTotalEstimateTime(totalEstimateTimeString);
		
		resultReport.setTotalTimeSpent(totalTimeSpentString);
		
		return resultReport;
	}
	
	@Override
	public OfficeTaskReportInforWrapper getOfficeTaskReport(int officeId) {
		OfficeTaskReportInforWrapper officeTaskReport = new OfficeTaskReportInforWrapper();
		
		String officeAddress = officeRepository.findById(officeId).get().getAddress();
		
		officeTaskReport.setOfficeAddress(officeAddress);
		
		List<Integer> staffIdList = jdbcTemplate.query("SELECT st.id AS staff_id" +
				" FROM staff AS st" +
				" INNER JOIN office AS o" + 
				" ON st.office_id = o.id" +
				" WHERE o.id = ?" , new ListStaffIdRowMapper() , officeId);
		
		if (staffIdList.isEmpty()) {
			return officeTaskReport;
		}
		
		for(Integer staffId : staffIdList) {
			
			String staffName = internalStaffRepository.findById(staffId).get().getFullName();
			
			TaskReportInforWrapper staffTaskReport = getReportFromStaff(staffId);
			
			officeTaskReport.getStaffTasksReport().put(staffName, staffTaskReport);
			
		}
		
		return officeTaskReport;
	}
	
	private long calculateTotalTimeSpent(List<TaskRowInforWrapper> taskRows) {
		
		long result = 0;
		
		if(taskRows.isEmpty()) {
			return result;
		}
		
		for(TaskRowInforWrapper aTaskRow : taskRows) {
		
			switch (aTaskRow.getStatus()) {
				case NOT_STARTED:
					continue;
			case IN_PROGRESS:
					long timeSpent = new Date().getTime() - aTaskRow.getDateStart().getTime();
					result += timeSpent;
					break;
					
				case FINISHED:
					long timeSpent1 = aTaskRow.getDateFinish().getTime() - aTaskRow.getDateStart().getTime();
					result+= timeSpent1;
					break;
			
			}
			
		}
		
		return result;
		
	}

	private String convertFromMilisToString(long totalTime) {
		
		long hours = TimeUnit.MILLISECONDS.toHours(totalTime);
		
		long minutesInMilis = totalTime - TimeUnit.HOURS.toMillis(hours);
		
		long minutes = TimeUnit.MILLISECONDS.toMinutes(minutesInMilis);
		
		String result = String.format("%d hours : %d minutes", hours , minutes);
		
		
		return result;
	}

	private long calculateTotalEstimateTime(List<TaskRowInforWrapper> taskRows) {
		
		long result = 0;
		
		if(taskRows.isEmpty()) {
			return result;
		}
		
		for (TaskRowInforWrapper aTaskRow : taskRows) {
			
			long timeEstimateForATask = aTaskRow.getDateFinish().getTime() - aTaskRow.getDateStart().getTime();
			
			result += timeEstimateForATask;
			
		}
		
		return result;
	}

}
