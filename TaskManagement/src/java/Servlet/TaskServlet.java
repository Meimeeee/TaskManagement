/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.TaskDAO;
import DTO.TaskDTO;
import java.io.IOException;
import java.sql.SQLException;
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
public class TaskServlet extends HttpServlet {

    private String TASK = "Task/task.jsp";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int projectId = Integer.parseInt(req.getParameter("projectId"));
            TaskDAO dao = TaskDAO.getInstance();
            List<TaskDTO> tasks = dao.getList(projectId);
            String projectName = dao.getProjectName(projectId);
            req.setAttribute("projectName", projectName);
            req.setAttribute("tasks", tasks);

        } catch (SQLException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(TaskServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(TaskServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        req.getRequestDispatcher(TASK).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idString = req.getParameter("taskId");
            int id = Integer.parseInt(idString);
            String link = req.getParameter("link");
            String status;
            if (link == null || link.trim().isEmpty()) {
                status = "In Progress";
            } else {
                status = "Done";
            }

            TaskDAO dao = TaskDAO.getInstance();
            dao.update(new TaskDTO(id, null, null, 0, null, status, null, null, null, link));

        } catch (SQLException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(TaskServlet.class.getName()).log(Level.SEVERE, null, ex);
            req.getRequestDispatcher("Task/task.jsp").forward(req, resp);
        } catch (ClassNotFoundException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(TaskServlet.class.getName()).log(Level.SEVERE, null, ex);
            req.getRequestDispatcher(TASK).forward(req, resp);
        }

        resp.sendRedirect("task");
    }

}
