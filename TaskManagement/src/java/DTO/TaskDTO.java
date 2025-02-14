/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ngoct
 */
public class TaskDTO {

    private int taskId;
    private String taskName;
    private String taskDescription;
    private int projectId;
    private String assignedTo;
    private String taskStatus;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDate dueDate;
    private String linkSubmission;

    public TaskDTO() {
    }

    public TaskDTO(int taskId, String taskName, String taskDescription, int projectId, String assignedTo, String taskStatus, LocalDateTime createAt, LocalDateTime updateAt, LocalDate dueDate, String linkSubmission) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.projectId = projectId;
        this.assignedTo = assignedTo;
        this.taskStatus = taskStatus;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.dueDate = dueDate;
        this.linkSubmission = linkSubmission;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getLinkSubmission() {
        return linkSubmission;
    }

    public void setLinkSubmission(String linkSubmission) {
        this.linkSubmission = linkSubmission;
    }

}
