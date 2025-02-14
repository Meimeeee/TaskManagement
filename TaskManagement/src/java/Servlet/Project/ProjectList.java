/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Project;

import DAO.ProjectDAO;
import DTO.ProjectDTO;
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
@WebServlet(name = "ProjectList", urlPatterns = "/project")
public class ProjectList extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                int accountId = (Integer) session.getAttribute("id");
                
                ProjectDAO projectDAO = new ProjectDAO();
                List<ProjectDTO> projects = projectDAO.getProjectList(accountId);
                if (projects != null) {
                    req.setAttribute("projects", projects);
                } else {
                    req.setAttribute("error", "No Project Found!");
                }
                req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("login-servlet");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(ProjectList.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
        }
    }
    
}
