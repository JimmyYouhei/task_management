package com.hocvoichuyengia.spring.task_management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="office")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Office {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="address")
	private String address;
	
	@OneToOne
	@JoinColumn(name="person_in_charge_id")
	private Staff personInCharge;
	
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

	
	public Office() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Staff getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(Staff personInCharge) {
		this.personInCharge = personInCharge;
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

