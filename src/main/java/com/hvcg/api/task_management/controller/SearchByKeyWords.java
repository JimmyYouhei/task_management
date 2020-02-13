package com.hvcg.api.task_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.dao.OfficeRepository;
import com.hvcg.api.task_management.dao.ProjectRespository;
import com.hvcg.api.task_management.dao.StaffRepository;
import com.hvcg.api.task_management.dao.SubtaskRepository;
import com.hvcg.api.task_management.dao.TaskCategoryRepository;
import com.hvcg.api.task_management.dao.TeamRepository;
import com.hvcg.api.task_management.entity.Office;
import com.hvcg.api.task_management.entity.Project;
import com.hvcg.api.task_management.entity.Staff;
import com.hvcg.api.task_management.entity.Subtask;
import com.hvcg.api.task_management.entity.TaskCategory;
import com.hvcg.api.task_management.entity.Team;

@RestController
@RequestMapping("/search")
public class SearchByKeyWords {

	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	ProjectRespository projectRepository;
	
	@Autowired
	TaskCategoryRepository taskCategoryRepository;
	
	@Autowired
	SubtaskRepository subtaskRepository;
	
	@Autowired
	OfficeRepository officeRepository;
	
	@GetMapping("/staff/{keyword}")
	public List<Staff> searchAllStaffNameContain(@PathVariable String keyword){
		
		return staffRepository.findByFullNameLike("%"+keyword+"%");
		
	}
	
	
	@GetMapping("/team/{keyword}")
	public List<Team> searchAllTeamNameContain(@PathVariable String keyword){
		
		return teamRepository.findByNameLike("%" + keyword + "%");
		
	}
	
	@GetMapping("/project/{keyword}")
	public List<Project> searchAllProjectNameContain(@PathVariable String keyword){
		
		return projectRepository.findByNameLike("%" + keyword + "%");
		
	}
	
	@GetMapping("taskCategory/{keyword}")
	public List<TaskCategory> searchAllTaskCategoryNameContain(@PathVariable String keyword){
		
		return taskCategoryRepository.findByNameLike("%" + keyword + "%");
		
	}
	
	@GetMapping("subtask/{keyword}")
	public List<Subtask> searchAllSubtaskNameContain(@PathVariable String keyword){
		
		return subtaskRepository.findByNameLike("%" + keyword + "%");
		
	}
	
	@GetMapping("office/{keyword}")
	public List<Office> searchAllOfficeAddressContain(@PathVariable String keyword){
		
		return officeRepository.findByAddressLike("%" + keyword + "%");
		
	}
	
}
