package com.hvcg.api.task_management.model;

import com.hvcg.api.task_management.entity.Project;

public class ProjectWithTaskStatusReport {
    private Project theProject;

    private TaskReportWithStatus taskReportWithStatus;

    public ProjectWithTaskStatusReport() {
    }

    public ProjectWithTaskStatusReport(Project theProject, TaskReportWithStatus taskReportWithStatus) {
        this.theProject = theProject;
        this.taskReportWithStatus = taskReportWithStatus;
    }

    public Project getTheProject() {
        return theProject;
    }

    public void setTheProject(Project theProject) {
        this.theProject = theProject;
    }

    public TaskReportWithStatus getTaskReportWithStatus() {
        return taskReportWithStatus;
    }

    public void setTaskReportWithStatus(TaskReportWithStatus taskReportWithStatus) {
        this.taskReportWithStatus = taskReportWithStatus;
    }
}
