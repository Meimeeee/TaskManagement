/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.ProjectMember;

import DAO.AccountDAO;
import DAO.ProjectMemberDAO;
import DTO.ProjectMemberDTO;
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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                String projectRaw = req.getParameter("projectId");
                int projectId = Integer.parseInt(projectRaw);
                String username = req.getParameter("username");
                AccountDAO accountDAO = new AccountDAO();
                int accountId = accountDAO.getIdByUsername(username);  
                if (accountId > 0) {
                    ProjectMemberDAO memberDAO = new ProjectMemberDAO();
                    int found = memberDAO.checkProjectMember(projectId, accountId);
                    if (found == 0) {
                        int result = memberDAO.addMember(new ProjectMemberDTO(projectId, accountId, "member"));
                        if (result > 0) {
                            resp.sendRedirect("project-info?projectId=" + projectId);
                            return;
                        } else {
                            req.setAttribute("error", "Cannot add member to project!");
                        }
                    } else {
                        req.setAttribute("error", "This account is already a member of this project!");
                    }
                } else {
                    req.setAttribute("error", "Account not found!");
                }
                req.getRequestDispatcher("project-info").forward(req, resp);
            } else {
                resp.sendRedirect("login-servlet");
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(AddMember.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
        }
    }
}
