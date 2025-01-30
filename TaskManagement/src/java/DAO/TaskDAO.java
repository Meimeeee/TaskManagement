/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TaskDTO;
import JDBC.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngoct
 */
public class TaskDAO {

    private static TaskDAO instance;

    public static TaskDAO getInstance() {
        if (instance == null) {
            instance = new TaskDAO();
        }
        return instance;
    }

    public List<TaskDTO> getList() throws SQLException, SQLException, ClassNotFoundException {
        List<TaskDTO> tasks = new ArrayList<>();
        Connection connect = Connect.getConnect();
        PreparedStatement ps = connect.prepareStatement("SELECT \n"
                + "    t.*, \n"
                + "    a.username AS assigned_username\n"
                + "FROM \n"
                + "    Task t\n"
                + "JOIN \n"
                + "    Account a \n"
                + "ON \n"
                + "    t.assigned_to = a.account_id;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TaskDTO t = new TaskDTO(rs.getInt("task_id"),
                    rs.getString("task_name"),
                    rs.getString("task_description"),
                    rs.getInt("project_id"),
                    rs.getString("assigned_username"),
                    rs.getString("task_status"),
                    rs.getDate("create_at").toLocalDate(),
                    rs.getDate("update_at").toLocalDate(),
                    rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null,
                    rs.getString("link_submission"));
            tasks.add(t);
        }
        return tasks;
    }
    
    public void update(TaskDTO t) throws SQLException, ClassNotFoundException{
        Connection connect = Connect.getConnect();
        PreparedStatement ps = connect.prepareStatement("UPDATE Task SET link_submission = ?, "
                + "task_status = ? WHERE task_id = ?");
        ps.setString(1, t.getLinkSubmission());
        ps.setString(2, t.getTaskStatus());
        ps.setInt(3, t.getTaskId());
        ps.execute();
    }

}
