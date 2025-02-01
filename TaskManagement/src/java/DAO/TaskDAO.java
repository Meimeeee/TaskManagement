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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    public List<TaskDTO> getList(int projectID) throws SQLException, SQLException, ClassNotFoundException {
        List<TaskDTO> tasks = new ArrayList<>();
        Connection connect = Connect.getConnect();
        PreparedStatement ps = connect.prepareStatement("SELECT \n"
                + "    t.*, \n"
                + "    a.username AS assigned_username\n"
                + "FROM \n"
                + "    task t\n"
                + "JOIN \n"
                + "    account a \n"
                + "ON \n"
                + "    t.assigned_to = a.account_id "
                + "WHERE project_id = ?");
        ps.setInt(1, projectID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TaskDTO t = new TaskDTO(rs.getInt("task_id"),
                    rs.getString("task_name"),
                    rs.getString("task_description"),
                    rs.getInt("project_id"),
                    rs.getString("assigned_username"),
                    rs.getString("task_status"),
                    rs.getTimestamp("create_at").toLocalDateTime(),
                    rs.getTimestamp("update_at").toLocalDateTime(),
                    rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null,
                    rs.getString("link_submission"));
            tasks.add(t);
        }
        return tasks;
    }
    
    public String getProjectName(int projectId) throws SQLException, ClassNotFoundException{
        Connection connect = Connect.getConnect();
        PreparedStatement ps = connect.prepareStatement("SELECT project_name FROM project WHERE project_id = ?");
        ps.setInt(1, projectId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getString("project_name");
        }
        return null;
    }

    public void update(TaskDTO t) throws SQLException, ClassNotFoundException {
        Connection connect = Connect.getConnect();
        PreparedStatement ps = connect.prepareStatement("UPDATE task SET link_submission = ?, "
                + "task_status = ? WHERE task_id = ?");
        ps.setString(1, t.getLinkSubmission());
        ps.setString(2, t.getTaskStatus());
        ps.setInt(3, t.getTaskId());
        ps.execute();
    }

    public void add(TaskDTO t, String assigned) throws SQLException, ClassNotFoundException {
        Connection connect = Connect.getConnect();
        PreparedStatement ps = connect.prepareStatement("INSERT INTO task (\n"
                + "    task_id, \n"
                + "    task_name, \n"
                + "    task_description, \n"
                + "    project_id, \n"
                + "    assigned_to, \n"
                + "    task_status, \n"
                + "    create_at, \n"
                + "    update_at, \n"
                + "    due_date, \n"
                + "    link_submission\n"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setInt(1, t.getTaskId());
        ps.setString(2, t.getTaskName());
        ps.setString(3, t.getTaskDescription());
        ps.setInt(4, t.getProjectId());
        ps.setString(5, assigned);
        ps.setString(6, t.getTaskStatus());
        ps.setTimestamp(7, Timestamp.valueOf(t.getCreateAt()));
        ps.setTimestamp(8, Timestamp.valueOf(t.getUpdateAt()));
        ps.setDate(9, java.sql.Date.valueOf(t.getDueDate()));
        ps.setString(10, t.getLinkSubmission());
        ps.execute();
    }
    
    public void delete(int taskId) throws SQLException, ClassNotFoundException{
        Connection connect = Connect.getConnect();
        PreparedStatement ps = connect.prepareStatement("DELETE FROM task WHERE task_id = ?");
        ps.setInt(1, taskId); 
        ps.execute();
    }
}
