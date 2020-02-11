package com.hvcg.api.task_management.dto;

import com.hvcg.api.task_management.entity.Team;
import com.hvcg.api.task_management.entity.TeamProject;

public class TeamAndAssignment {
	
	Team team;
	TeamProject assignmentDetail;
	
	public TeamAndAssignment(Team team, TeamProject assignmentDetail) {
		super();
		this.team = team;
		this.assignmentDetail = assignmentDetail;
	}

	public TeamAndAssignment() {
		super();
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public TeamProject getAssignmentDetail() {
		return assignmentDetail;
	}

	public void setAssignmentDetail(TeamProject assignmentDetail) {
		this.assignmentDetail = assignmentDetail;
	}
	
	

}
