package com.hvcg.api.task_management.model;

import java.util.Date;

import com.hvcg.api.task_management.constant.Status;

public class TaskRowInforWrapper {
	
	private int taskId;
	
	private Date dateStart;
	private Date dateFinish;
	
	private Status status;

	public TaskRowInforWrapper() {
		super();
	}

	public TaskRowInforWrapper(int taskId, Date dateStart, Date dateFinish, Status status) {
		super();
		this.taskId = taskId;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.status = status;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
}
