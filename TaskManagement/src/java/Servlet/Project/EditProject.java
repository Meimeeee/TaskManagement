/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Project;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import Exceptions.InvalidDataException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh Han Dong
 */
@WebServlet(name = "EditProject", urlPatterns = "/edit-project")
public class EditProject extends HttpServlet {
    private final ProjectDAO projectDAO = new ProjectDAO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                String id = req.getParameter("projectId");
                int projectId = Integer.parseInt(id);
                ProjectDTO project = projectDAO.getProjectById(projectId);
                if (project != null) {
                    req.setAttribute("project", project);
                } else {
                    throw new InvalidDataException("Cannot get project by id!");
                }
            } else {
                resp.sendRedirect("login-servlet");
                return;
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(EditProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/editProject.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("Project/editProject.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("projectId");
            int projectId = Integer.parseInt(id);
            String projectName = req.getParameter("projectName");
            String projectDescription = req.getParameter("description");
            String accountId = req.getParameter("accountId");
            int createBy = Integer.parseInt(accountId);
            String creatAtString = req.getParameter("createAt");
            LocalDate createAt = LocalDate.parse(creatAtString);
            LocalDate updateAt = LocalDate.now();
            String projectStatus = req.getParameter("status");

            int result = projectDAO.updateProject(new ProjectDTO(projectId, projectName, projectDescription, createBy, createAt, updateAt, projectStatus));
            if (result == 0) {
                throw new InvalidDataException("Cannot update project in database!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(EditProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
        }
        resp.sendRedirect("project");
    }
}
