package com.hvcg.api.task_management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.dao.SubtaskRepository;
import com.hvcg.api.task_management.dao.TaskCategoryRepository;
import com.hvcg.api.task_management.dto.SwitchDto;
import com.hvcg.api.task_management.entity.Subtask;
import com.hvcg.api.task_management.entity.TaskCategory;

@RestController
@RequestMapping("/switch")
public class SwapController {
	
	@Autowired
	SubtaskRepository subtaskRepository;
	
	@Autowired
	TaskCategoryRepository taskCategoryRepository;
	
	@PatchMapping("/taskCategory/subtask")
	public Subtask swapTaskCategory(@RequestBody SwitchDto dto){
		
		int subtaskId = dto.getSubtaskId();
		
		int taskCategoryToSwitchId = dto.getTaskCategoryId();
		
		Subtask subtask = subtaskRepository.getOne(subtaskId);
		
		TaskCategory taskCategoryToSwitch = taskCategoryRepository.getOne(taskCategoryToSwitchId);
		
		
		subtask.setTaskCategory(taskCategoryToSwitch);
		
		return subtaskRepository.saveAndFlush(subtask);
		
	}

	
}
