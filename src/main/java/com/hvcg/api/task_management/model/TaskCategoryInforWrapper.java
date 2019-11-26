package com.hvcg.api.task_management.model;

import java.util.ArrayList;
import java.util.List;


public class TaskCategoryInforWrapper {
	
	private String taskCategoryName;
	
	private String taskCategoryDescription;
	
	private List<SubTaskInforWrapper> subtasks = new ArrayList<>();

	public TaskCategoryInforWrapper() {
		super();
	}

	public TaskCategoryInforWrapper(String taskCategoryName, String taskCategoryDescription,
			List<SubTaskInforWrapper> subtasks) {
		super();
		this.taskCategoryName = taskCategoryName;
		this.taskCategoryDescription = taskCategoryDescription;
		this.subtasks = subtasks;
	}

	public String getTaskCategoryName() {
		return taskCategoryName;
	}

	public void setTaskCategoryName(String taskCategoryName) {
		this.taskCategoryName = taskCategoryName;
	}

	public String getTaskCategoryDescription() {
		return taskCategoryDescription;
	}

	public void setTaskCategoryDescription(String taskCategoryDescription) {
		this.taskCategoryDescription = taskCategoryDescription;
	}

	public List<SubTaskInforWrapper> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<SubTaskInforWrapper> subtasks) {
		this.subtasks = subtasks;
	}
	
	public boolean containSubtaskCategoryName(SubTaskInforWrapper subtask) {
		
		if(subtasks.isEmpty()) {
			return false;
		}
		
		for (SubTaskInforWrapper aSubtask : subtasks) {
			
			if(aSubtask.getSubtaskName().equals(subtask.getSubtaskName())) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public int positionOfSubtask(SubTaskInforWrapper subtask) {
		
		for(int i = 0; i < subtasks.size() ; i++) {
			
			if(subtasks.get(i).getSubtaskName().equals(subtask.getSubtaskName())) {
				return i;
			}
			
		}
		
		return -1;
		
	}

}
