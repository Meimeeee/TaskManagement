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
@WebServlet(name = "AddProject", urlPatterns = "/add-project")
public class AddProject extends HttpServlet {
    private final ProjectDAO projectDAO = new ProjectDAO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer id = (Integer) session.getAttribute("id");
        req.setAttribute("id", id);
        req.getRequestDispatcher("Project/addProject.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String projectName = req.getParameter("projectName");
            String projectDescription = req.getParameter("description");
            String accountId = req.getParameter("createBy");
            Integer createBy = Integer.parseInt(accountId);
            LocalDate createAt = LocalDate.now();
            LocalDate updateAt = LocalDate.now();

            int result = projectDAO.addProject(new ProjectDTO(projectName, projectDescription, createBy, createAt, updateAt, "InProgress"));
            if (result == 0) {
                throw new InvalidDataException("Cannot add project to database!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(AddProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectList.jsp").forward(req, resp);
        }
        resp.sendRedirect("project");
    }
}
