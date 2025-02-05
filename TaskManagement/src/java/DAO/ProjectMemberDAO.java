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
    private static final String GET_PROJECT_MEMBER_LIST = "SELECT username, role FROM Project_member m INNER JOIN Account a ON m.project_member_id = a.? WHERE project_id = ?";
    
    public ArrayList<ProjectMemberDTO> getProjectList(int accountId, int projectId) {
        ArrayList<ProjectMemberDTO> memberList = new ArrayList<>();
        try(Connection conn = Connect.getConnect();
                PreparedStatement statement = conn.prepareStatement(GET_PROJECT_MEMBER_LIST)) {
            statement.setInt(1, accountId);
            statement.setInt(2, projectId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String username = result.getString("username");
                String role = result.getString("role");
                
                memberList.add(new ProjectMemberDTO(username, role));
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return memberList;
    }
}
