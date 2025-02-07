/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ProjectDAO;
import DTO.AccountDTO;
import DTO.ProjectDTO;
import Exceptions.InvalidDataException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
@WebServlet(name = "ProjectController", urlPatterns = "/project")
public class ProjectController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProjectDAO projectDAO = new ProjectDAO();
        /*
        HttpSession session = request.getSession(false);
        AccountDTO account = (AccountDTO) session.getAttribute("account");

        if (session == null || account == null) {
            response.sendRedirect("login-servlet");
            return;
        }
        */
        String action = request.getParameter("action");
        
        if (action == null) {
            list(request, response, projectDAO);
        } else {
            //if (account.getRole() == "ProjectManager") {
            if (true) { // Luc merge code phan log in phai sua lai
                switch (action) {
                    case "prepare-add":
                        prepareAdd(request, response, projectDAO);
                        break;
                    case "add":
                        addProject(request, response, projectDAO);
                        break;
                    case "prepare-edit":
                        prepareEdit(request, response, projectDAO);
                        break;
                    case "edit":
                        editProject(request, response, projectDAO);
                        break;
                    case "delete-confirm":
                        deleteConfirm(request, response, projectDAO);
                        break;
                    case "delete":
                        deleteProject(request, response, projectDAO);
                        break;
                    default:
                        list(request, response, projectDAO);
                        break;
                }
            } else {
                list(request, response, projectDAO);
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    private void list(HttpServletRequest req, HttpServletResponse resp, ProjectDAO projectDAO) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(true);
            session.setAttribute("id", 1);
            List<ProjectDTO> projects = projectDAO.getProjectList(1);
            if (projects != null && !projects.isEmpty()) {
               req.setAttribute("projects", projects);
            } else {
               throw new InvalidDataException("No Project Found!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, e);
        }
        req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
    }
    
    private void prepareAdd(HttpServletRequest req, HttpServletResponse resp, ProjectDAO projectDAO) throws ServletException, IOException {
        
    }

    private void addProject(HttpServletRequest req, HttpServletResponse resp, ProjectDAO projectDAO) throws ServletException, IOException {
        try {
            String projectName = req.getParameter("projectName");
            String projectDescription = req.getParameter("description");
            String accountId = req.getParameter("accountId");
            int createBy = Integer.parseInt(accountId);
            LocalDate createAt = LocalDate.now();
            LocalDate updateAt = LocalDate.now();

            int result = projectDAO.addProject(new ProjectDTO(projectName, projectDescription, createBy, createAt, updateAt, "InProgress"));
            if (result == 0) {
                throw new InvalidDataException("Cannot add project to database!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
    }
    
    private void prepareEdit(HttpServletRequest req, HttpServletResponse resp, ProjectDAO projectDAO) throws ServletException, IOException {
        
    }
    
    private void editProject(HttpServletRequest req, HttpServletResponse resp, ProjectDAO projectDAO) throws ServletException, IOException {
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
            String projectStatus = req.getParameter("projectStatus");

            int result = projectDAO.updateProject(new ProjectDTO(projectName, projectDescription, createBy, createAt, updateAt, projectStatus));
            if (result == 0) {
                throw new InvalidDataException("Cannot update project in database!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
    }
    
    private void deleteConfirm(HttpServletRequest req, HttpServletResponse resp, ProjectDAO projectDAO) throws ServletException, IOException {
        
    }
    
    private void deleteProject(HttpServletRequest req, HttpServletResponse resp, ProjectDAO projectDAO) throws ServletException, IOException {
        try {
            String id = req.getParameter("projectId");
            int projectId = Integer.parseInt(id);

            int result = projectDAO.deleteProject(projectId);
            if (result == 0) {
                throw new InvalidDataException("Cannot delete project in database!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
    }
}
