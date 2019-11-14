package com.hocvoichuyengia.spring.task_management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="subtask")
public class Subtask {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "task_category_id")
	private TaskCategory taskCategoryId;
	
	
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

	public Subtask() {
		super();
	}

	public Subtask(int id, String name, String description, TaskCategory taskCategoryId, Date dateStart,
			Date dateFinish, Status status, String note, Staff createBy, Date createTime, Staff updateBy,
			Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.taskCategoryId = taskCategoryId;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.status = status;
		this.note = note;
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

	public TaskCategory getTaskCategoryId() {
		return taskCategoryId;
	}

	public void setTaskCategoryId(TaskCategory taskCategoryId) {
		this.taskCategoryId = taskCategoryId;
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
	
	
}
