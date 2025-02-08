/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProjectDTO;
import JDBC.Connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
            statement.setInt(4, project.getAccountId());
            statement.setDate(5, Date.valueOf(project.getCreateAt()));
            statement.setDate(6, Date.valueOf(project.getUpdateAt()));
            result = statement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException e) {
            System.out.println("SQL Exception in adding project.");
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public int updateProject(ProjectDTO project) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "UPDATE Project set project_name = ?, project_description = ?, project_status = ?, update_at = ? WHERE = project_id = ?";
        try (Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());
            statement.setString(3, project.getProjectStatus());
            statement.setDate(4, Date.valueOf(project.getUpdateAt()));
            statement.setInt(5, project.getProjectId());
            result = statement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException e) {
            System.out.println("SQL Exception in updating project.");
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
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
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException e) {
            System.out.println("SQL Exception in deleting project.");
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public ProjectDTO getProjectInfo(int projectId) throws SQLException, ClassNotFoundException {
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
                LocalDate createAt = result.getDate("create_at").toLocalDate();
                LocalDate updateAt = result.getDate("update_at").toLocalDate();
                
                project = new ProjectDTO(projectId, projectName, projectDescription, createBy, createAt, updateAt, projectStatus);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
            throw ex;
        } catch (SQLException e) {
            System.out.println("SQL Exception in getting project info.");
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return project;
    }
    
    public ArrayList<ProjectDTO> getProjectList(int accountId) throws SQLException, ClassNotFoundException {
        ArrayList<ProjectDTO> projectList = new ArrayList<>();
        String query = "SELECT project_id, project_name, update_at, project_status FROM Project WHERE create_by = ?";
        try(Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, accountId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int projectId = result.getInt("project_id");
                String projectName = result.getString("project_name");
                LocalDate updateAt = result.getDate("update_at").toLocalDate();
                String projectStatus = result.getString("project_status");
                
                projectList.add(new ProjectDTO(projectId, projectName, updateAt, projectStatus));
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
            throw ex;
        } catch (SQLException e) {
            System.out.println("SQL Exception in getting project list.");
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
        return projectList;
    }
}
