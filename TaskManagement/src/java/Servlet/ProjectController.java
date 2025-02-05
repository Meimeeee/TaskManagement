/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh Han Dong
 */
public class ProjectController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("accountId");
            int accountId = Integer.parseInt(id);
            ProjectDAO projectDAO = new ProjectDAO();
            List<ProjectDTO> projects = projectDAO.getProjectList(accountId);
            req.setAttribute("projects", projects);
        } catch (ClassNotFoundException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

        req.getRequestDispatcher("Project/project.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String projectName = req.getParameter("projectName");
            String projectDescription = req.getParameter("description");
            String accountId = req.getParameter("accountId");
            int createBy = Integer.parseInt(accountId);
            LocalDate createAt = LocalDate.now();
            LocalDate updateAt = LocalDate.now();

            ProjectDAO projectDAO = new ProjectDAO();
            projectDAO.addProject(new ProjectDTO(projectName, projectDescription, createBy, createAt, updateAt, "In Progress"));

        } catch (ClassNotFoundException ex) {
            req.setAttribute("error", ex.getMessage());
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            req.getRequestDispatcher("Project/project.jsp").forward(req, resp);
        }

        resp.sendRedirect("projects");
    }
}
