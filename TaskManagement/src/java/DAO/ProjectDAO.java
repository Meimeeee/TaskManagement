/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProjectDTO;
import JDBC.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh Han Dong
 */
public class ProjectDAO {
    public int addProject(ProjectDTO project) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "INSERT INTO Project(project_name, project_description, project_status, create_by, create_at, update_at) VALUES (?,?,?,?,?,?)";
        try (Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());
            statement.setString(3, project.getProjectStatus());
            statement.setInt(4, project.getCreateBy());
            statement.setTimestamp(5, project.getCreateAt());
            statement.setTimestamp(6, project.getUpdateAt());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in adding project.", e);
        }
        return result;
    }
    
    public int updateProject(ProjectDTO project) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "UPDATE Project set project_name = ?, project_description = ?, project_status = ?, update_at = ? WHERE project_id = ?";
        try (Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());
            statement.setString(3, project.getProjectStatus());
            statement.setTimestamp(4, project.getUpdateAt());
            statement.setInt(5, project.getProjectId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in updating project.", e);
        }
        return result;
    }
    
    public int deleteProject(int projectId) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "DELETE FROM Project WHERE project_id = ?";
        try (Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, projectId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in deleting project.", e);
        }
        return result;
    }
    
    public ProjectDTO getProjectById(int projectId) throws SQLException, ClassNotFoundException {
        ProjectDTO project = null;
        String query = "SELECT * FROM Project WHERE project_id = ?";
        try(Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, projectId);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                String projectName = result.getString("project_name");
                String projectDescription = result.getString("project_description");
                String projectStatus = result.getString("project_status");
                int createBy = result.getInt("create_by");
                Timestamp createAt = result.getTimestamp("create_at");
                Timestamp updateAt = result.getTimestamp("update_at");
                
                project = new ProjectDTO(projectId, projectName, projectDescription, createBy, createAt, updateAt, projectStatus);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in getting project info.", e);
        }
        return project;
    }
    
    public ArrayList<ProjectDTO> getProjectList(int accountId) throws SQLException, ClassNotFoundException {
        ArrayList<ProjectDTO> projectList = new ArrayList<>();
        String query = "SELECT p.project_id, project_name, update_at, project_status FROM Project p INNER JOIN Project_member m ON p.project_id = m.project_id WHERE account_id = ?";
        try(Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, accountId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int projectId = result.getInt("project_id");
                String projectName = result.getString("project_name");
                Timestamp updateAt = result.getTimestamp("update_at");
                String projectStatus = result.getString("project_status");
                
                projectList.add(new ProjectDTO(projectId, projectName, updateAt, projectStatus));
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in getting project list.", e);
        }
        return projectList;
    }
    
    public int getProjectId() throws SQLException, ClassNotFoundException {
        int projectId = 0;
        String query = "SELECT MAX(project_id) AS project_id FROM Project";
        try(Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                projectId = result.getInt("project_id");
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in getting newest projectId.", e);
        }
        return projectId;
    }
}
