package com.hvcg.api.task_management.model;


/**
 * 
 * class to contain Task Report Summary
 * 
 * @author JY
 *
 */

public class TaskReportInforWrapper {
	
	private int numberOfTasks;
	
	private String totalEstimateTime;
	
	private String totalTimeSpent;

	public TaskReportInforWrapper() {
		super();
	}

	public TaskReportInforWrapper(int numberOfTasks, String totalEstimateTime, String totalTimeSpent) {
		super();
		this.numberOfTasks = numberOfTasks;
		this.totalEstimateTime = totalEstimateTime;
		this.totalTimeSpent = totalTimeSpent;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public String getTotalEstimateTime() {
		return totalEstimateTime;
	}

	public void setTotalEstimateTime(String totalEstimateTime) {
		this.totalEstimateTime = totalEstimateTime;
	}

	public String getTotalTimeSpent() {
		return totalTimeSpent;
	}

	public void setTotalTimeSpent(String totalTimeSpent) {
		this.totalTimeSpent = totalTimeSpent;
	}
	
	
	
}