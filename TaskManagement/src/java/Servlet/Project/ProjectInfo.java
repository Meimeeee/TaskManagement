/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Project;

import DAO.AccountDAO;
import DAO.ProjectDAO;
import DAO.ProjectMemberDAO;
import DTO.ProjectDTO;
import DTO.ProjectMemberDTO;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "ProjectInfo", urlPatterns = "/project-info")
public class ProjectInfo extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                String id = req.getParameter("projectId");
                int projectId = Integer.parseInt(id);
                
                ProjectDAO projectDAO = new ProjectDAO();
                ProjectDTO project = projectDAO.getProjectById(projectId);
                if (project != null) {
                    req.setAttribute("project", project);
                } else {
                    req.setAttribute("error", "Project not found!");
                }
                AccountDAO accountDAO = new AccountDAO();
                String name = accountDAO.getUsernameById(project.getCreateBy());
                if (name != null) {
                    req.setAttribute("createdBy", name);
                } else {
                    req.setAttribute("error", "Cannot get create by from database!");
                }
                ProjectMemberDAO memberDAO = new ProjectMemberDAO();
                List<ProjectMemberDTO> memberList = memberDAO.getMemberList(projectId);
                if (memberList != null) {
                    req.setAttribute("members", memberList);
                } else {
                    req.setAttribute("error", "Cannot get project member list!");
                }
                req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("login-servlet");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(ProjectInfo.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
        }
    }
    
}
