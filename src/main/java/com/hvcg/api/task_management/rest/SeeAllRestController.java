package com.hvcg.api.task_management.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.dao.ProjectRespository;
import com.hvcg.api.task_management.dao.SubtaskRepository;
import com.hvcg.api.task_management.dao.TeamRepository;
import com.hvcg.api.task_management.dto.TeamAndAssignment;
import com.hvcg.api.task_management.entity.Project;
import com.hvcg.api.task_management.entity.Staff;
import com.hvcg.api.task_management.entity.StaffSubtask;
import com.hvcg.api.task_management.entity.StaffTeam;
import com.hvcg.api.task_management.entity.Subtask;
import com.hvcg.api.task_management.entity.Team;
import com.hvcg.api.task_management.entity.TeamProject;

@RestController
@RequestMapping("/see")
public class SeeAllRestController {
	
	@Autowired
	ProjectRespository projectRespository;
	
	@Autowired
	SubtaskRepository subtaskRepository;
	
	@Autowired
	TeamRepository teamRepository;

	@GetMapping("/all/team/project/{projectId}")
	public List<TeamAndAssignment> getAllTeamFromProject(@PathVariable int projectId){
		
		Project thisProject = projectRespository.getOne(projectId);
		
		List<TeamProject> allTeamProjects = thisProject.getTeamProject();
		
		if(allTeamProjects.size()<1) {
			return null;
		}
		
		List<TeamAndAssignment> result = new ArrayList<TeamAndAssignment>();
		
		for(TeamProject aTeamProject : allTeamProjects) {
			
			Team aTeam = aTeamProject.getTeam();
			
			TeamAndAssignment toReturn = new TeamAndAssignment(aTeam, aTeamProject);
			
			result.add(toReturn);
			
		}
		
		return result;
		
	}
	
	@GetMapping("/all/staff/subtask/{subtaskId}")
	public List<Staff> getAllStaffFromSubtaskId(@PathVariable int subtaskId){
		
		Subtask thisSubtask = subtaskRepository.getOne(subtaskId);
		
		List<StaffSubtask> allStaffSubtaskAssignments = thisSubtask.getStaffAssignedToSubtask();
		
		if(allStaffSubtaskAssignments.size() < 1) {
			return null;
		}
		
		List<Staff> result = new ArrayList<>();
		
		for(StaffSubtask aStaffSubtaskAssignment : allStaffSubtaskAssignments ) {
			
			Staff staff = aStaffSubtaskAssignment.getStaff();
			
			result.add(staff);
			
		}
		
		return result;
		
	}
	
	@GetMapping("/all/staff/team/{teamId}")
	public List<Staff> getAllStaffFromTeamId (@PathVariable int teamId) {
		
		Team thisTeam = teamRepository.getOne(teamId);
		
		List<StaffTeam> staffTeamAssignments = thisTeam.getStaffTeamAssignments();
		
		if(staffTeamAssignments.size() < 1) {
			
			return null;
			
		}
		
		List<Staff> result = new ArrayList<>();
		
		for (StaffTeam aStaffTeamAssignment : staffTeamAssignments) {
			
			Staff staff = aStaffTeamAssignment.getStaff();
			
			result.add(staff);
			
		}
		
		return result;
		
	}
	
}
