package com.hvcg.api.task_management.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * class to contain project report infor
 * 
 * @author JY
 *
 */

public class ProjectInforWrapper {
	
	private String projectName;
	
	private String projectDescription;
	
	private List<TaskCategoryInforWrapper> taskCategories = new ArrayList<>();

	public ProjectInforWrapper() {
		super();
	}

	public ProjectInforWrapper(String projectName, String projectDescription,
			List<TaskCategoryInforWrapper> taskCategories) {
		super();
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.taskCategories = taskCategories;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public List<TaskCategoryInforWrapper> getTaskCategories() {
		return taskCategories;
	}

	public void setTaskCategories(List<TaskCategoryInforWrapper> taskCategories) {
		this.taskCategories = taskCategories;
	}

	public boolean containTaskCategoryName(TaskCategoryInforWrapper taskCategory) {
		
		if(taskCategories.isEmpty()) {
			return false;
		}
		
		for (TaskCategoryInforWrapper aTaskCategory : taskCategories) {
			
			if(aTaskCategory.getTaskCategoryName().equals(taskCategory.getTaskCategoryName())) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public int positionOfTaskCategory(TaskCategoryInforWrapper taskCategory) {
		
		for(int i = 0; i < taskCategories.size() ; i++) {
			
			if(taskCategories.get(i).getTaskCategoryName().equals(taskCategory.getTaskCategoryName())) {
				return i;
			}
			
		}
		
		return -1;
		
	}
	

}
