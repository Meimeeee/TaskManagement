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
    private final ProjectDAO projectDAO = new ProjectDAO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
            HttpSession session = req.getSession(true); // sau nay nho xoa
            session.setAttribute("id", 1); // sau nay nho xoa
            Integer accountId = (Integer) session.getAttribute("id");
            List<ProjectDTO> projects = projectDAO.getProjectList(accountId);
            if (projects != null && !projects.isEmpty()) {
               req.setAttribute("projects", projects);
            } else {
               throw new InvalidDataException("No Project Found!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(ProjectList.class.getName()).log(Level.SEVERE, null, e);
        }
        req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
    }
    
}
