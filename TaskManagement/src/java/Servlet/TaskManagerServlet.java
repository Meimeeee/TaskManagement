/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.AccountDAO;
import DAO.TaskDAO;
import DTO.AccountDTO;
import DTO.TaskDTO;
import Exceptions.TaskException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngoct
 */
public class TaskManagerServlet extends HttpServlet {

    private String TASK_MANAGER = "Task/task-manager.jsp";
    private String TASK_EDIT = "Task/edit-task.jsp";
    private String TASK_ADD = "Task/add-task.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = TASK_MANAGER;
        try {
            int projectId = Integer.parseInt(req.getParameter("projectId"));

            TaskDAO taskDao = TaskDAO.getInstance();
            List<TaskDTO> tasks = taskDao.getTask(projectId);
            String projectName = taskDao.getProjectName(projectId);
            req.setAttribute("projectName", projectName);
            req.setAttribute("tasks", tasks);

            List<AccountDTO> accounts = AccountDAO.getAllAccounts(projectId);
            req.setAttribute("accounts", accounts);

            String func = req.getParameter("func");
            if (func != null) {
                switch (func) {
                    case "edit":
                        int taskId = Integer.parseInt(req.getParameter("taskId"));
                        TaskDTO t = taskDao.getTaskByTaskId(projectId, taskId);
                        req.setAttribute("t", t);

                        url = TASK_EDIT;
                        break;

                    case "add":
                        url = TASK_ADD;
                        break;
                }
            }

        } catch (SQLException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(TaskMemberServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(TaskMemberServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String func = req.getParameter("func");
            switch (func) {
                case "edit":
                    editTask(req, resp);
                    break;

                case "delete":
                    delete(req, resp);
                    break;

                case "add":
                    addTask(req, resp);
                    break;
            }
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(TASK_MANAGER).forward(req, resp);
            Logger.getLogger(TaskMemberServlet.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher(TASK_MANAGER).forward(req, resp);
            Logger.getLogger(TaskManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TaskException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(TaskManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException, ServletException {
        try {
//            int taskId = Integer.parseInt(req.getParameter("taskId"));
            int projectId = Integer.parseInt(req.getParameter("projectId"));
            String taskName = req.getParameter("taskName");
            String taskDescription = req.getParameter("taskDescription");
            int assigned = Integer.parseInt(req.getParameter("assignedTo"));
            LocalDate dueDate = LocalDate.parse(req.getParameter("dueDate"));
            LocalDateTime createAt = LocalDateTime.now();
            LocalDateTime updateAt = LocalDateTime.now();

            TaskDTO task = new TaskDTO(0, taskName, taskDescription, projectId, null, null, createAt, updateAt, dueDate, null);
            TaskDAO dao = TaskDAO.getInstance();
            dao.add(task, assigned);

            resp.sendRedirect("task-manager?projectId=" + projectId);

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Wrong number format");
            Logger.getLogger(TaskMemberServlet.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher(TASK_ADD).forward(req, resp);
        } catch (SQLException ex) {
            req.setAttribute("error", "SQL error: " + ex.getSQLState());
            Logger.getLogger(TaskManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            req.getRequestDispatcher(TASK_ADD).forward(req, resp);
        }

    }

    private void editTask(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, IOException, ServletException, TaskException {
        int projectId = Integer.parseInt(req.getParameter("projectId"));
        TaskDAO taskDao = TaskDAO.getInstance();
        try {
            int taskId = Integer.parseInt(req.getParameter("taskId"));
            String taskName = req.getParameter("taskName");
            String taskDescription = req.getParameter("taskDescription");
            int assigned = Integer.parseInt(req.getParameter("assignedTo"));
            LocalDate dueDate = LocalDate.parse(req.getParameter("dueDate"));
            LocalDateTime updateAt = LocalDateTime.now();
            TaskDTO task = new TaskDTO(taskId, taskName, taskDescription, projectId, null, null, null, updateAt, dueDate, null);
            taskDao.edit(task, assigned);

            resp.sendRedirect("task-manager?projectId=" + projectId);

        } catch (NumberFormatException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(TaskMemberServlet.class.getName()).log(Level.SEVERE, null, e);

            List<AccountDTO> accounts = AccountDAO.getAllAccounts(projectId);
            req.setAttribute("accounts", accounts);
            int taskId = Integer.parseInt(req.getParameter("taskId"));
            TaskDTO t = taskDao.getTaskByTaskId(projectId, taskId);
            req.setAttribute("t", t);

            req.getRequestDispatcher(TASK_EDIT).forward(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int projectId = Integer.parseInt(req.getParameter("projectId"));
        try {
            int taskId = Integer.parseInt(req.getParameter("taskId"));
            TaskDAO dao = TaskDAO.getInstance();
            dao.delete(taskId);

        } catch (NumberFormatException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(TaskMemberServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        resp.sendRedirect("task-manager?projectId=" + projectId);
    }

}
