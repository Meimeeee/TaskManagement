/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author Huynh Han Dong
 */
public class ProjectDTO {
    private int projectId;
    private String projectName;
    private LocalDate updateAt;
    private String projectStatus;
    
    public ProjectDTO() {  
    }
    
    public ProjectDTO(int projectId, String projectName, LocalDate updateAt, String projectStatus) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.updateAt = updateAt;
        this.projectStatus = projectStatus;
    }
    
    public int getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public LocalDate getUpdateAt() {
        return updateAt;
    }
    
    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }
    
    public String getProjectStatus() {
        return projectStatus;
    }
    
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}
