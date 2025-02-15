/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.ProjectMember;

import DAO.ProjectDAO;
import DAO.ProjectMemberDAO;
import Exceptions.InvalidDataException;
import Servlet.Project.DeleteProject;
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
@WebServlet(name = "DeleteMember", urlPatterns = {"/delete-member"})
public class DeleteMember extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String projectRaw = req.getParameter("projectId");
            int projectId = Integer.parseInt(projectRaw);
            String accountRaw = req.getParameter("accountId");
            int accountId = Integer.parseInt(accountRaw);

            ProjectMemberDAO memberDAO = new ProjectMemberDAO();
            int result = memberDAO.deleteMember(accountId, projectId);
            if (result == 0) {
                throw new InvalidDataException("Cannot delete project member in database!");
            }
            resp.sendRedirect("project-info?projectId=" + projectId);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(DeleteProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
        }
    }
}
