package com.hvcg.api.task_management.model;

public class TaskReportStatusStaff {

    private int taskAssigned;

    private int taskCompleted;

    public TaskReportStatusStaff() {
    }

    public TaskReportStatusStaff(int taskAssigned, int taskCompleted) {
        this.taskAssigned = taskAssigned;
        this.taskCompleted = taskCompleted;
    }

    public int getTaskAssigned() {
        return taskAssigned;
    }

    public void setTaskAssigned(int taskAssigned) {
        this.taskAssigned = taskAssigned;
    }

    public int getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(int taskCompleted) {
        this.taskCompleted = taskCompleted;
    }
}
