package com.hvcg.api.task_management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.dao.ProjectRespository;
import com.hvcg.api.task_management.dao.StaffSubtaskRepository;
import com.hvcg.api.task_management.dao.SubtaskRepository;
import com.hvcg.api.task_management.entity.Project;
import com.hvcg.api.task_management.entity.StaffSubtask;
import com.hvcg.api.task_management.entity.Subtask;
import com.hvcg.api.task_management.entity.TaskCategory;
import com.hvcg.api.task_management.model.OfficeReport;
import com.hvcg.api.task_management.model.OfficeTaskReportInforWrapper;
import com.hvcg.api.task_management.model.ProjectWithTaskStatusReport;
import com.hvcg.api.task_management.model.StaffReport;
import com.hvcg.api.task_management.model.TaskReportInforWrapper;
import com.hvcg.api.task_management.model.TaskReportStatusStaff;
import com.hvcg.api.task_management.model.TaskReportWithStatus;
import com.hvcg.api.task_management.service.OfficeReportService;
import com.hvcg.api.task_management.service.StaffReportService;
import com.hvcg.api.task_management.service.TaskReportService;

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

	@Autowired
	private ProjectRespository projectRespository;
	
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

	@GetMapping("/task/project/{projectId}/count")
	public  TaskReportWithStatus  getTaskStatusReportByProject(@PathVariable int projectId){
		Project project = projectRespository.getOne(projectId);

		Set<TaskCategory> taskCategoriesOfProject = project.getTaskCategories();

		int totalTask = 0;
		int totalNotStartedTask = 0;
		int totalInProgressTask = 0;
		int totalFinishTask = 0;

		for (TaskCategory aTaskCategory : taskCategoriesOfProject){
			Set<Subtask> subtasks = aTaskCategory.getSubtasks();

			for(Subtask aSubTask : subtasks) {

				totalTask++;

				switch (aSubTask.getStatus()){
					case NOT_STARTED:
						totalNotStartedTask++;
						break;

					case IN_PROGRESS:
						totalInProgressTask++;
						break;

					case FINISHED:
						totalFinishTask++;
						break;

					default:
				}

			}
		}

		TaskReportWithStatus taskReportWithStatus = new TaskReportWithStatus(totalTask ,
				totalNotStartedTask , totalInProgressTask , totalFinishTask);

		return taskReportWithStatus;
	}

	@GetMapping("task/projects/count")
    public List<ProjectWithTaskStatusReport> getProjectAndTaskStatusReport (){

	    List<Project> allProject = projectRespository.findAll();

	    List<ProjectWithTaskStatusReport> report = new ArrayList<>();

	    if(allProject.size() < 1){
	        return null;
        }

	    for (Project project : allProject){
	        TaskReportWithStatus taskReportWithStatus = getTaskStatusReportByProject(project.getId());

	        ProjectWithTaskStatusReport projectWithTaskStatusReport = new ProjectWithTaskStatusReport(
	                project , taskReportWithStatus
            );

	        report.add(projectWithTaskStatusReport);
        }

	    return report;

    }
	
}
