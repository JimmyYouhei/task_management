package com.hvcg.api.task_management.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.dao.StaffRepository;
import com.hvcg.api.task_management.dao.StaffSubtaskRepository;
import com.hvcg.api.task_management.dao.StaffTeamRepository;
import com.hvcg.api.task_management.dao.SubtaskRepository;
import com.hvcg.api.task_management.dao.TeamProjectRepository;
import com.hvcg.api.task_management.entity.Staff;
import com.hvcg.api.task_management.entity.StaffTeam;
import com.hvcg.api.task_management.entity.Subtask;
import com.hvcg.api.task_management.entity.TeamProject;
import com.hvcg.api.task_management.exception.NoSubtaskException;

@RestController
@RequestMapping("/assignable")
public class AssignableStaff {

	@Autowired
	SubtaskRepository subtaskRepository;
	
	@Autowired
	TeamProjectRepository teamProjectRepository;
	
	@Autowired
	StaffTeamRepository staffTeamRepository;
	
	@Autowired
	StaffSubtaskRepository staffSubtaskRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@GetMapping("/subtask/{id}")
	List <Staff> assignableStaffs (@PathVariable int id) throws NoSubtaskException {
		// find the subtask
		Subtask subtask = subtaskRepository.findById(id).get();
		
		// find the subtask projectId
		int projectId = subtask.getTaskCategory().getProject().getId();
		
		List<TeamProject> teamProjects = teamProjectRepository.findByProjectId(projectId);
	
		List<Integer> teamIds = new ArrayList<>(); 
		for(TeamProject eachTeamProject: teamProjects) {
			if(!teamIds.contains(eachTeamProject.getTeam().getId())) {
				teamIds.add(eachTeamProject.getTeam().getId());
			}
		}
		
		
		
		List<Staff> staffCanBeAssignable = new ArrayList<>();
		
		for(Integer teamId : teamIds) {
			
			List<StaffTeam> found = staffTeamRepository.findByTeamId(teamId);
			
			for(StaffTeam eachStaffTeamFound : found) {
				
				if(!staffCanBeAssignable.contains(eachStaffTeamFound.getStaff())) {
					staffCanBeAssignable.add(eachStaffTeamFound.getStaff());
				}
			}
		}
		
		return staffCanBeAssignable;

		
	}
	

}
