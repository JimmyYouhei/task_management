package com.hvcg.api.task_management.dto;

import javax.validation.constraints.NotNull;

public class SwitchDto {
	
	@NotNull
	private int subtaskId;
	
	@NotNull
	private int taskCategoryId;

	public SwitchDto() {
		super();
	}

	public SwitchDto(@NotNull int subtaskId, @NotNull int taskCategoryId) {
		super();
		this.subtaskId = subtaskId;
		this.taskCategoryId = taskCategoryId;
	}

	public int getSubtaskId() {
		return subtaskId;
	}

	public void setSubtaskId(int subtaskId) {
		this.subtaskId = subtaskId;
	}

	public int getTaskCategoryId() {
		return taskCategoryId;
	}

	public void setTaskCategoryId(int taskCategoryId) {
		this.taskCategoryId = taskCategoryId;
	}

}
