package com.hocvoichuyengia.spring.task_management.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="staff_team")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffTeam{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne 
	@JoinColumn(name= "staff_id")
	private Staff staffId;
	
	@ManyToOne
	@JoinColumn(name ="team_id")
	private Team teamId;
	
	
	@OneToOne
	@JoinColumn(name="create_by")
	private Staff createBy;
	
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	
	@OneToOne
	@JoinColumn(name="update_by")
	private Staff updateBy;
	
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public StaffTeam() {
		
	}

	public StaffTeam(int id, Staff staffId, Team teamId, Staff createBy, Date createTime, Staff updateBy,
			Date updateTime) {
		this.id = id;
		this.staffId = staffId;
		this.teamId = teamId;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Staff getStaffId() {
		return staffId;
	}

	public void setStaffId(Staff staffId) {
		this.staffId = staffId;
	}

	public Team getTeamId() {
		return teamId;
	}

	public void setTeamId(Team teamId) {
		this.teamId = teamId;
	}

	public Staff getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Staff createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Staff getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Staff updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}




