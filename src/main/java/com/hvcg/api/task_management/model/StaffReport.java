package com.hvcg.api.task_management.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.map.LinkedMap;

public class StaffReport {
	
	private int id;
	
	private String fullName;
	
	private Date dateOfBirth;
	
	private String phoneNumber;
	
	private String email;
	
	private String facebook;
	
	private String username;
	
	private String role;
	
	private String officeAddress;
	
	private LinkedMap<String, String> teams = new LinkedMap<>();
	
	private LinkedMap<String, String> projectsParticipated = new LinkedMap<>();
	
	//private LinkedMap<String , String> taskCategories = new LinkedMap<>();
	
	private List<ProjectInforWrapper> subtasksDetail = new ArrayList<>();

	public StaffReport() {
		super();
	}

	public StaffReport(int id, String fullName, Date dateOfBirth, String phoneNumber, String email, String facebook,
			String username, String role, String officeAddress, LinkedMap<String, String> teams,
			LinkedMap<String, String> projectsParticipated, List<ProjectInforWrapper> subtasksDetail) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.facebook = facebook;
		this.username = username;
		this.role = role;
		this.officeAddress = officeAddress;
		this.teams = teams;
		this.projectsParticipated = projectsParticipated;
		this.subtasksDetail = subtasksDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public LinkedMap<String, String> getTeams() {
		return teams;
	}

	public void setTeams(LinkedMap<String, String> teams) {
		this.teams = teams;
	}

	public LinkedMap<String, String> getProjectsParticipated() {
		return projectsParticipated;
	}

	public void setProjectsParticipated(LinkedMap<String, String> projectsParticipated) {
		this.projectsParticipated = projectsParticipated;
	}

	public List<ProjectInforWrapper> getSubtasksDetail() {
		return subtasksDetail;
	}

	public void setSubtasksDetail(List<ProjectInforWrapper> subtasksDetail) {
		this.subtasksDetail = subtasksDetail;
	}
	
	
	
	public boolean containProjectName(ProjectInforWrapper project) {
		
		if(subtasksDetail.isEmpty()) {
			return false;
		}
		
		for (ProjectInforWrapper projectInforWrapper : subtasksDetail) {
			if (projectInforWrapper.getProjectName().equals(project.getProjectName())){
				return true;
			} 
		}
		
		return false;
		
	}
	
	public int positionOfProject(ProjectInforWrapper project) {
		
		for(int i = 0; i < subtasksDetail.size() ; i++) {
			
			if(subtasksDetail.get(i).getProjectName().equals(project.getProjectName())) {
				return i;
			}
			
		}
		
		return -1;
		
	}
	
}
