/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Project;

import DAO.ProjectDAO;
import DAO.ProjectMemberDAO;
import DTO.ProjectDTO;
import DTO.ProjectMemberDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
@WebServlet(name = "AddProject", urlPatterns = "/add-project")
public class AddProject extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Project/addProject.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                String projectName = req.getParameter("projectName");
                String projectDescription = req.getParameter("description");
                String createByRaw = req.getParameter("createBy");
                int createBy = Integer.parseInt(createByRaw);
                LocalDateTime dateTime = LocalDateTime.now();
                Timestamp createAt = Timestamp.valueOf(dateTime);
                Timestamp updateAt = Timestamp.valueOf(dateTime);
                
                ProjectDAO projectDAO = new ProjectDAO();
                int result = projectDAO.addProject(new ProjectDTO(projectName, projectDescription, createBy, createAt, updateAt, "InProgress"));
                if (result > 0) {
                    int projectId = projectDAO.getProjectId();
                    if (projectId > 0) {
                        ProjectMemberDAO memberDAO = new ProjectMemberDAO();
                        int added = memberDAO.addMember(new ProjectMemberDTO(projectId, createBy, "manager"));
                        if (added > 0) {
                            resp.sendRedirect("project-info?projectId=" + projectId);
                            return;
                        } else {
                            req.setAttribute("error", "Cannot add project member to database!");
                        }
                    } else {
                        req.setAttribute("error", "Cannot get the newest projectId!");
                    }
                } else {
                    req.setAttribute("error", "Cannot add project member to database!");
                }
                req.getRequestDispatcher("Project/AddProject.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("login-servlet");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(AddProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/AddProject.jsp").forward(req, resp);
        }
    }
}
