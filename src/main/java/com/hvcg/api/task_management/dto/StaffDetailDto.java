package com.hvcg.api.task_management.dto;

import java.util.Date;

/**
 * 
 * Data transfer object to quickly handle JSON staff data
 * 
 * @author JY
 *
 */


public class StaffDetailDto {
	
	private String fullName;
	
	private Date dateOfBirth;
	
	private String phoneNumber;
	
	private String email;
	
	private String facebook; 
	
	

	public StaffDetailDto() {
		super();
	}
	
	

	public StaffDetailDto(String fullName, Date dateOfBirth, String phoneNumber, String email, String facebook,
			int officeId) {
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.facebook = facebook;
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

	
	
	
	

}
