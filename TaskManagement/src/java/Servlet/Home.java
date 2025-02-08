/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet("/home")
public class Home extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(true); // sau nay nho xoa
        session.setAttribute("id", 1);  // Sau nay nho xoa

        if (session == null || account == null) {
            resp.sendRedirect("LogIn/login.jsp");
            return;
        }
        String url = "project";
        String action = req.getParameter("action");
        
        ProjectDAO projectDAO = new ProjectDAO();
        
        if (action == null) {
            list(req, resp, projectDAO);
        } else {
            //if (account.getRole() == "ProjectManager") {
            if (true) { // Luc merge code phan login phai sua lai
                switch (action) {
                    case "add":
                        url = "add-project";
                        break;
                    case "edit":
                        url = "edit-project";
                        break;
                    case "delete":
                        url = "delete=project";
                        break;
                    default:
                        url= "project";
                        break;
                }
            } else {
                list(req, resp, projectDAO);
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    */
    }
    
}
