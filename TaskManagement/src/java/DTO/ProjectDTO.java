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
    private String projectDescription;
    private int createBy;
    private LocalDate createAt;
    private LocalDate updateAt;
    private String projectStatus;
    
    public ProjectDTO() {  
    }
    
    public ProjectDTO(int projectId, String projectName, String projectDescription, int createBy, LocalDate createAt, LocalDate updateAt, String projectStatus) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createBy = createBy;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.projectStatus = projectStatus;
    }
    
    public ProjectDTO(String projectName, String projectDescription, int createBy, LocalDate createAt, LocalDate updateAt, String projectStatus) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createBy = createBy;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.projectStatus = projectStatus;
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
    public String getProjectDescription() {
        return projectDescription;
    }
    
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    
    public int getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    
    public LocalDate getCreateAt() {
        return createAt;
    }
    
    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
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
