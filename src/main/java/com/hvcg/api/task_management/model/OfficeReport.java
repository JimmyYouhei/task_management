package com.hvcg.api.task_management.model;

import java.util.ArrayList;
import java.util.List;

import com.hvcg.api.task_management.entity.InternalStaff;

/**
 * class to contain office report
 * 
 * @author JY
 *
 */

public class OfficeReport {
	
	private int id;
	
	private String officeAddress;
	
	private InternalStaff personInCharge;
	
	private List<StaffReport> staffs = new ArrayList<>();

	public OfficeReport() {
		super();
	}
	
	public OfficeReport(int id, String officeAddress, InternalStaff personInCharge, List<StaffReport> staffs) {
		super();
		this.id = id;
		this.officeAddress = officeAddress;
		this.personInCharge = personInCharge;
		this.staffs = staffs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public InternalStaff getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(InternalStaff personInCharge) {
		this.personInCharge = personInCharge;
	}

	public List<StaffReport> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<StaffReport> staffs) {
		this.staffs = staffs;
	}

	public boolean containStaffReport(StaffReport staffReport) {
		if (staffs.isEmpty()) {
			return false;
		}
		
		return staffs.contains(staffReport);
		
	}
}
