/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author Huynh Han Dong
 */
public class ProjectDTO {
    private int projectId;
    private String projectName;
    private String projectDescription;
    private int createBy;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String projectStatus;
    
    public ProjectDTO() {  
    }
    
    public ProjectDTO(int projectId, String projectName, String projectDescription, int createBy, Timestamp createAt, Timestamp updateAt, String projectStatus) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createBy = createBy;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.projectStatus = projectStatus;
    }
    
    public ProjectDTO(String projectName, String projectDescription, int createBy, Timestamp createAt, Timestamp updateAt, String projectStatus) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createBy = createBy;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.projectStatus = projectStatus;
    }
    
    public ProjectDTO(int projectId, String projectName, Timestamp updateAt, String projectStatus) {
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
    
    public Timestamp getCreateAt() {
        return createAt;
    }
    
    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
    
    public Timestamp getUpdateAt() {
        return updateAt;
    }
    
    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
    
    public String getProjectStatus() {
        return projectStatus;
    }
    
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}
