package com.hvcg.api.task_management.rest;

import com.hvcg.api.task_management.constant.Status;
import com.hvcg.api.task_management.dao.StaffSubtaskRepository;
import com.hvcg.api.task_management.dao.SubtaskRepository;
import com.hvcg.api.task_management.entity.Staff;
import com.hvcg.api.task_management.entity.StaffSubtask;
import com.hvcg.api.task_management.entity.Subtask;
import com.hvcg.api.task_management.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hvcg.api.task_management.service.OfficeReportService;
import com.hvcg.api.task_management.service.StaffReportService;
import com.hvcg.api.task_management.service.TaskReportService;

import java.util.List;

/**
 * 
 * REST API to see the report 
 * 
 * @author JY
 *
 */

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/report")
public class ReportRestController {
	
	@Autowired
	private StaffReportService staffReportService;
	
	@Autowired
	private OfficeReportService officeReportService;
	
	@Autowired
	private TaskReportService taskReportService;

	@Autowired
	private SubtaskRepository subtaskRepository;

	@Autowired
	private StaffSubtaskRepository staffSubtaskRepository;
	
	@GetMapping("/staff/{staffId}")
	public StaffReport getStaffReportById(@PathVariable int staffId) {
		
		return staffReportService.getFullStaffReportById(staffId);
	}
	
	
	@GetMapping("/office/{officeId}")
	public OfficeReport getOfficeReportById(@PathVariable int officeId) {
		return officeReportService.getOfficeReportByOfficeId(officeId);
	}

	@GetMapping("/task")
	public TaskReportInforWrapper getAllTasksReport() {
		return taskReportService.getFullTaskReport();
	}

	@GetMapping("/task/status/count")
	public TaskReportWithStatus getTaskStatusReport(){

		List<Subtask> allSubtask = subtaskRepository.findAll();

		int totalTask = allSubtask.size();

		int totalInProgressTask = 0;

		int totalNotStartTask = 0;

		int totalFinishedTask = 0;

		for(Subtask aSubtask : allSubtask){
			switch (aSubtask.getStatus()){
				case NOT_STARTED:
					totalNotStartTask++;
					break;

				case IN_PROGRESS:
					totalInProgressTask++;
					break;

				case FINISHED:
					totalFinishedTask++;
					break;

				default:
			}
		}

		TaskReportWithStatus report = new TaskReportWithStatus(totalTask,
				totalNotStartTask , totalInProgressTask , totalFinishedTask);

		return report;
	}

	@GetMapping("/staff/subtask")
	public TaskReportStatusStaff getSubtaskReportOfStaff (@RequestParam(required = true) int staffId ,
														  @RequestParam(required = true) int month ,
														  @RequestParam (required = true) int year){

		List<StaffSubtask> staffSubtasks = staffSubtaskRepository.queryByStaffIdAndMonth(staffId , month , year);

		int totalTaskAssigned = staffSubtasks.size();



		List<StaffSubtask> staffSubtaskFinished =
				staffSubtaskRepository.queryFinishByStaffIdAndMonth(staffId , month , year);

		int totalTaskCompleted = staffSubtaskFinished.size();

		TaskReportStatusStaff report = new TaskReportStatusStaff(totalTaskAssigned , totalTaskCompleted);

		return report;
	}

	
	@GetMapping("/task/staff/{staffId}")
	public TaskReportInforWrapper getFullTaskReportFromStaff(@PathVariable int staffId) {
		
		return taskReportService.getFullTaskReportFromStaff(staffId);
		
	}
	
	@GetMapping("/task/office/{officeId}")
	public OfficeTaskReportInforWrapper getOfficeTaskReport(@PathVariable int officeId) {
		return taskReportService.getOfficeTaskReport(officeId);
	}
	
}
