/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProjectDTO;
import entities.Project;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectJDBC;

/**
 *
 * @author Huynh Han Dong
 */
public class ProjectDAO {
    private static final String ADD_PROJECT = "INSET INTO Project(project_name, project_description, project_status, create_by, create_at, update_at) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_PROJECT = "DELETE FROM Project WHERE project_id = ?";
    private static final String GET_PROJECT_BY_ID = "SELECT * FROM Project WHERE project_id = ?";
    private static final String GET_PROJECT_LIST = "SELECT project_id, project_name, update_at, project_status FROM Project";
    
    public int addProject(Project project) {
        int result = 0;
        try (Connection conn = ConnectJDBC.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_PROJECT)) {
            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());
            statement.setString(3, project.getProjectStatus());
            statement.setInt(4, project.getCreateBy());
            statement.setDate(5, Date.valueOf(project.getCreateAt()));
            statement.setDate(6, Date.valueOf(project.getUpdateAt()));
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public int updateProject(int projectId, LocalDate updateAt, String column ,String newValue) {
        int result = 0;
        String UPDATE_PROJECT = "UPDATE Project set " + column + "= " + newValue + ", update_at = ? WHERE = project_id = ?";
        try (Connection conn = ConnectJDBC.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_PROJECT)) {
            statement.setString(1, newValue);
            statement.setDate(2, Date.valueOf(updateAt));
            statement.setInt(3, projectId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public int deleteProject(int projectId) {
        int result = 0;
        try (Connection conn = ConnectJDBC.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_PROJECT)) {
            statement.setInt(1, projectId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public Project getProjectById(int projectId) {
        Project project = null;
        try(Connection conn = ConnectJDBC.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_PROJECT_BY_ID)) {
            statement.setInt(1, projectId);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                String projectName = result.getString("project_name");
                String projectDescription = result.getString("project_description");
                String projectStatus = result.getString("project_status");
                int createBy = result.getInt("create_by");
                LocalDate createAt = result.getDate("create_at").toLocalDate();
                LocalDate updateAt = result.getDate("update_at").toLocalDate();
                
                project = new Project(projectId, projectName, projectDescription, createBy, createAt, updateAt, projectStatus);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return project;
    }
    
    public ArrayList<ProjectDTO> getProjectList(int projectId) {
        ArrayList<ProjectDTO> projectList = new ArrayList<>();
        try(Connection conn = ConnectJDBC.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_PROJECT_LIST)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String projectName = result.getString("project_name");
                LocalDate updateAt = result.getDate("update_at").toLocalDate();
                String projectStatus = result.getString("project_status");
                
                projectList.add(new ProjectDTO(projectId, projectName, updateAt, projectStatus));
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return projectList;
    }
}
