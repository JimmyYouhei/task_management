package com.hvcg.api.task_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvcg.api.task_management.model.OfficeReport;
import com.hvcg.api.task_management.repository.OfficeReportRepository;

@Service
public class OfficeReportServiceImpl implements OfficeReportService{

	@Autowired
	OfficeReportRepository officeReportRepository;
	
	@Override
	public OfficeReport getOfficeReportByOfficeId(int officeId) {
		
		return officeReportRepository.getOfficeReportById(officeId);
	}

}
