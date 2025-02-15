/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Project;

import DAO.ProjectDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huynh Han Dong
 */
@WebServlet(name = "DeleteProject", urlPatterns = "/delete-project")
public class DeleteProject extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("projectId");
            int projectId = Integer.parseInt(id);

            ProjectDAO projectDAO = new ProjectDAO();
            int result = projectDAO.deleteProject(projectId);
            if (result == 0) {
                req.setAttribute("error", "Cannot delete project!");
                req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("project");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(DeleteProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
        }
    }
}
