/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Huynh Han Dong
 */
public class ProjectMemberDTO {
    private int projectMemberId;
    private int projectId;
    private int accountId;
    private String username;
    private String role;
    
    public ProjectMemberDTO() {
    }
    
    public ProjectMemberDTO(int projectMemberId, int projectId, int accountId, String role) {
        this.projectMemberId = projectMemberId;
        this.projectId = projectId;
        this.accountId = accountId;
        this.role = role;
    }
    
    public ProjectMemberDTO(int accountId, String username, String role) {
        this.accountId = accountId;
        this.username = username;
        this.role = role;
    }
    
    public ProjectMemberDTO(int projectId, int accountId, String role) {
        this.projectId = projectId;
        this.accountId = accountId;
        this.role = role;
    }
    
    public int getProjectMemberId() {
        return projectMemberId;
    }
    
    public void setProjectMemberId(int projectMemberId) {
        this.projectMemberId = projectMemberId;
    }
    
    public int getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
