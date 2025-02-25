/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Project;

import DAO.ProjectDAO;
import DAO.ProjectMemberDAO;
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
@WebServlet(name = "DeleteProject", urlPatterns = "/delete-project")
public class DeleteProject extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            String projectRaw = req.getParameter("projectId");
            int projectId = Integer.parseInt(projectRaw);
            
            ProjectMemberDAO memberDAO = new ProjectMemberDAO();
            int count = memberDAO.countProjectMember(projectId);
            switch (count) {
                case 0:
                    session.setAttribute("deleteError", "Cannot count project member!");
                    break;
                case 1:
                    String accountRaw = req.getParameter("accountId");
                    int accountId = Integer.parseInt(accountRaw);
                    int isOk = memberDAO.deleteMember(accountId, projectId);
                    if (isOk > 0) {
                        ProjectDAO projectDAO = new ProjectDAO();
                        int result = projectDAO.deleteProject(projectId);
                        if (result > 0) {
                            resp.sendRedirect("project");
                        } else {
                            session.setAttribute("deleteError", "Cannot add member to project!");
                        }
                    } else {
                        session.setAttribute("deleteError", "Cannot delete manager from this project!");
                    }
                    break;
                default:
                    session.setAttribute("deleteError", "Cannot delete project! There are other members in this project.");
                    break;
            }
            resp.sendRedirect("project-info?projectId=" + projectId);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            Logger.getLogger(DeleteProject.class.getName()).log(Level.SEVERE, null, e);
            req.getRequestDispatcher("Project/projectInfo.jsp").forward(req, resp);
        }
    }
}
