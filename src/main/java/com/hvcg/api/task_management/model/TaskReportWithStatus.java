package com.hvcg.api.task_management.model;

public class TaskReportWithStatus {
    private int totalTask;

    private int totalNotStartedTask;

    private int totalInProgressTask;

    private int totalFinishedTask;

    public TaskReportWithStatus() {

    }

    public TaskReportWithStatus(int totalTask, int totalNotStartedTask, int totalInProgressTask, int totalFinishedTask) {
        this.totalTask = totalTask;
        this.totalNotStartedTask = totalNotStartedTask;
        this.totalInProgressTask = totalInProgressTask;
        this.totalFinishedTask = totalFinishedTask;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(int totalTask) {
        this.totalTask = totalTask;
    }

    public int getTotalNotStartedTask() {
        return totalNotStartedTask;
    }

    public void setTotalNotStartedTask(int totalNotStartedTask) {
        this.totalNotStartedTask = totalNotStartedTask;
    }

    public int getTotalInProgressTask() {
        return totalInProgressTask;
    }

    public void setTotalInProgressTask(int totalInProgressTask) {
        this.totalInProgressTask = totalInProgressTask;
    }

    public int getTotalFinishedTask() {
        return totalFinishedTask;
    }

    public void setTotalFinishedTask(int totalFinishedTask) {
        this.totalFinishedTask = totalFinishedTask;
    }
}
