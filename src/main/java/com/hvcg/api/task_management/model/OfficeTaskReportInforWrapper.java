package com.hvcg.api.task_management.model;

import org.apache.commons.collections4.map.LinkedMap;

public class OfficeTaskReportInforWrapper {
	
	
	private String officeAddress;
	
	private LinkedMap< String , TaskReportInforWrapper> staffTasksReport = new LinkedMap<>();

	public OfficeTaskReportInforWrapper() {
		super();
	}

	public OfficeTaskReportInforWrapper(int id, String officeAddress,
			LinkedMap<String, TaskReportInforWrapper> staffTasksReport) {
		super();
		this.officeAddress = officeAddress;
		this.staffTasksReport = staffTasksReport;
	}


	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public LinkedMap<String, TaskReportInforWrapper> getStaffTasksReport() {
		return staffTasksReport;
	}

	public void setStaffTasksReport(LinkedMap<String, TaskReportInforWrapper> staffTasksReport) {
		this.staffTasksReport = staffTasksReport;
	}
	
}
