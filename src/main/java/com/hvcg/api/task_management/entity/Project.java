package com.hvcg.api.task_management.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * class to map project table 
 * 
 * JsonBackReference annotation is to avoid infinitive recursion 
 * 
 * 
 * @author JY
 *
 */

@Entity
@Table(name="project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name ="description")
	private String description;
	
	@Column(name = "note")
	private String note;
	
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
	
	@OneToMany(mappedBy = "project" , cascade = CascadeType.ALL)
	@JsonManagedReference("e")
	private Set<TaskCategory> taskCategories;
	
	@OneToMany(mappedBy = "project" , cascade = CascadeType.ALL)
	@JsonManagedReference("f")
	private List<TeamProject> teamProject;
	
	public Project() {
		
	}
	

	public Project(int id, String name, String description, String note, Staff createBy, Date createTime,
			Staff updateBy, Date updateTime, Set<TaskCategory> taskCategories) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.note = note;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.taskCategories = taskCategories;
	}

	public Project(int id, String name, String description, String note, Staff createBy, Date createTime,
			Staff updateBy, Date updateTime, Set<TaskCategory> taskCategories, List<TeamProject> teamProject) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.note = note;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.taskCategories = taskCategories;
		this.teamProject = teamProject;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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



	public Set<TaskCategory> getTaskCategories() {
		return taskCategories;
	}



	public void setTaskCategories(Set<TaskCategory> taskCategories) {
		this.taskCategories = taskCategories;
	}


	public List<TeamProject> getTeamProject() {
		return teamProject;
	}


	public void setTeamProject(List<TeamProject> teamProject) {
		this.teamProject = teamProject;
	}
	
	

}
