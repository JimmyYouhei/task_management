package com.hvcg.api.task_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="security")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column (name = "role")
	private String role;
	
	@OneToOne
	@JoinColumn(name = "staff_id")
	private InternalStaff staff;

	public User() {
		super();
	}

	public User( String username, String password, String role, InternalStaff staff) {

		this.username = username;
		this.password = password;
		this.role = role;
		this.staff = staff;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public InternalStaff getStaff() {
		return staff;
	}

	public void setStaff(InternalStaff staff) {
		this.staff = staff;
	}

}
