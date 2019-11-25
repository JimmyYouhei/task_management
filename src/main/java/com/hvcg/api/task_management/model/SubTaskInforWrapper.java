package com.hvcg.api.task_management.model;

import java.util.Date;

import com.hvcg.api.task_management.constant.Status;

public class SubTaskInforWrapper {
	
	private String subtaskName;
	
	private String subtaskDescription;
	
	private Date dateStart;
	
	private Date dateFinish;
	
	private Status status;

	public SubTaskInforWrapper() {
		super();
	}

	public SubTaskInforWrapper(String subtaskName, String subtaskDescription, Date dateStart, Date dateFinish,
			Status status) {
		super();
		this.subtaskName = subtaskName;
		this.subtaskDescription = subtaskDescription;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.status = status;
	}

	public String getSubtaskName() {
		return subtaskName;
	}

	public void setSubtaskName(String subtaskName) {
		this.subtaskName = subtaskName;
	}

	public String getSubtaskDescription() {
		return subtaskDescription;
	}

	public void setSubtaskDescription(String subtaskDescription) {
		this.subtaskDescription = subtaskDescription;
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
