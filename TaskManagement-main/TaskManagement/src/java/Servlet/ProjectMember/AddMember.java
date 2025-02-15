/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.ProjectMember;

import DAO.AccountDAO;
import DAO.ProjectMemberDAO;
import DTO.ProjectDTO;
import DTO.ProjectMemberDTO;
import Exceptions.InvalidDataException;
import Servlet.Project.AddProject;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "AddMember", urlPatterns = {"/add-member"})
public class AddMember extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
            if (session != null) {
                String action = req.getParameter("action");
                switch (action) { // thay bang if cung duoc
                    case "prepare-add":
                        getMember(req, resp);
                        break;
                    case "add":
                        addMember(req, resp);
                        break;
                }
            } else {
                resp.sendRedirect("login-servlet");
                return;
            }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("ProjectMember/addMember.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    protected void getMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                String username = req.getParameter("usesrname");
                AccountDAO accountDAO = new AccountDAO();
                int accountId = accountDAO.getIdByUsername(username);  
                if (accountId > 0) {
                    req.setAttribute("id", accountId);
                } else {
                    throw new InvalidDataException("Cannot get accounId from username!");
                }
            }    
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(AddProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
    }
    
    protected void addMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                String accountRaw = req.getParameter("accountId");
                int accountId= Integer.parseInt(accountRaw);
                String projectRaw = req.getParameter("projectId");
                int projectId= Integer.parseInt(projectRaw);
                String role = req.getParameter("role");
                ProjectMemberDAO memberDAO = new ProjectMemberDAO();
                int result = memberDAO.addMember(new ProjectMemberDTO(projectId, accountId, role));
                if (result == 0) {
                    throw new InvalidDataException("Cannot add project to database!");
                }
            } else {
                resp.sendRedirect("login-servlet");
                return;
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(AddProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
        }
        resp.sendRedirect("project-info");
    }

}
