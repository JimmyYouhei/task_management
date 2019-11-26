package com.hvcg.api.task_management.dto;

import javax.validation.constraints.NotNull;

/**
 * 
 * Data transfer object to quickly handle JSON security data
 * 
 * @author JY
 *
 */

public class LoginDto {

	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	private String role;
	
	private int staffId;

	public LoginDto() {
		super();
	}

	public LoginDto(@NotNull String username, @NotNull String password) {
		super();
		this.username = username;
		this.password = password;
	}

	
	
	public LoginDto(@NotNull String username, @NotNull String password, String role, int staffId) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.staffId = staffId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	
	
}
