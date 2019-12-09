package com.hvcg.api.task_management.entity;

import java.util.Date;
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


/**
 * 
 * class to map task_category table 
 * 
 * JsonBackReference annotation is to avoid infinitive recursion 
 * 
 * 
 * @author JY
 *
 */


@Entity
@Table(name="task_category")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	@JsonBackReference("gf")
	private Project project;
	
	@Column(name="name")
	private String name;
	
	@Column(name = "description")
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
	
	@OneToMany(mappedBy = "taskCategory" , cascade = CascadeType.ALL)
	private Set<Subtask> subtasks;

	public TaskCategory() {
		
	}

	public TaskCategory(int id, Project project, String name, String description, String note, Staff createBy,
			Date createTime, Staff updateBy, Date updateTime, Set<Subtask> subtasks) {
		super();
		this.id = id;
		this.project = project;
		this.name = name;
		this.description = description;
		this.note = note;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.subtasks = subtasks;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public Set<Subtask> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(Set<Subtask> subtasks) {
		this.subtasks = subtasks;
	}
	
}
