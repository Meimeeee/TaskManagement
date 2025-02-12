/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProjectMemberDTO;
import JDBC.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh Han Dong
 */
public class ProjectMemberDAO {
    public int addMember(ProjectMemberDTO projectMember) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Project_member(project_id, account_id, role_in_project) values (?,?,?)";
        int result = 0;
        try(Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, projectMember.getProjectId());
            statement.setInt(2, projectMember.getAccountId());
            statement.setString(3, projectMember.getRole());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in adding project member.", e);
        }
        return result;
    }
    
    public int deleteMember(int accountId, int projectId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM Project_member WHERE account_id =? and project_id =?";
        int result = 0;
        try(Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, accountId);
            statement.setInt(2, projectId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in deleting project member.", e);
        }
        return result;
    }
    
    public ArrayList<ProjectMemberDTO> getMemberList(int projectId) throws SQLException, ClassNotFoundException{
        String query = "SELECT m.account_id, username, role_in_project FROM Project_member m INNER JOIN Account a ON m.account_id = a.account_id WHERE project_id = ?";
        ArrayList<ProjectMemberDTO> memberList = new ArrayList<>();
        try(Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, projectId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int accountId = result.getInt("account_id");
                String username = result.getString("username");
                String role = result.getString("role_in_project");
                
                memberList.add(new ProjectMemberDTO(accountId, username, role));
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, "SQL Exception in getting project member list.", e);
        }
        return memberList;
    }
}
