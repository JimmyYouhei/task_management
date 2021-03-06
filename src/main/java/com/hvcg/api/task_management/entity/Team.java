package com.hvcg.api.task_management.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * class to map team table 
 * 
 * JsonBackReference annotation is to avoid infinitive recursion 
 * 
 * @author JY
 *
 */

@Entity
@Table(name="team")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy = "team" , cascade = CascadeType.ALL)
	@JsonBackReference("t")
	private List<StaffTeam> staffTeamAssignments;
	
	@ManyToOne
	@JoinColumn(name="create_by" , referencedColumnName = "id")
	@JsonBackReference("a")
	private Staff createBy;
	
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	
	@ManyToOne
	@JoinColumn(name="update_by" , referencedColumnName = "id")
	@JsonBackReference("b")
	private Staff updateBy;
	
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public Team() {
		super();
	}

	public Team(int id, String name, String description, Staff createBy, Date createTime, Staff updateBy,
			Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}

	public Team(int id, String name, String description, List<StaffTeam> staffTeamAssignments, Staff createBy,
			Date createTime, Staff updateBy, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.staffTeamAssignments = staffTeamAssignments;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<StaffTeam> getStaffTeamAssignments() {
		return staffTeamAssignments;
	}

	public void setStaffTeamAssignments(List<StaffTeam> staffTeamAssignments) {
		this.staffTeamAssignments = staffTeamAssignments;
	}
	
}
