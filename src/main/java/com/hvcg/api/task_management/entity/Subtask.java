package com.hvcg.api.task_management.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hvcg.api.task_management.constant.Status;

/**
 * 
 * class to map subtask table 
 * 
 * JsonBackReference annotation is to avoid infinitive recursion 
 * 
 * @author JY
 *
 */


@Entity
@Table(name="subtask")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subtask {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@JsonProperty("id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "task_category_id")
	@JsonBackReference("taskCategory")
	private TaskCategory taskCategory;
	
	
	@Column(name="date_start")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;
	
	@Column(name="date_finish")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFinish;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	

	@Column(name="note")
	private String note;
	
	@OneToMany(mappedBy = "subtask" , cascade = CascadeType.ALL)
	@JsonBackReference("f")
	private List<StaffSubtask> staffAssignedToSubtask;
	
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

	public Subtask() {
		super();
	}

	public Subtask(int id, String name, String description, TaskCategory taskCategory, Date dateStart, Date dateFinish,
			Status status, String note, Staff createBy, Date createTime, Staff updateBy, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.taskCategory = taskCategory;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.status = status;
		this.note = note;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}
	
	

	public Subtask(int id, String name, String description, TaskCategory taskCategory, Date dateStart, Date dateFinish,
			Status status, String note, List<StaffSubtask> staffAssignedToSubtask, Staff createBy, Date createTime,
			Staff updateBy, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.taskCategory = taskCategory;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.status = status;
		this.note = note;
		this.staffAssignedToSubtask = staffAssignedToSubtask;
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

	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public List<StaffSubtask> getStaffAssignedToSubtask() {
		return staffAssignedToSubtask;
	}

	public void setStaffAssignedToSubtask(List<StaffSubtask> staffAssignedToSubtask) {
		this.staffAssignedToSubtask = staffAssignedToSubtask;
	}
	
	
	
}
