package com.hvcg.api.task_management.dto;

import javax.validation.constraints.NotNull;

public class LoginDto {

	@NotNull
	private String username;
	
	@NotNull
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(@NotNull String username, @NotNull String password) {
		super();
		this.username = username;
		this.password = password;
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
	
}
