/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet.Identity;

import DTO.AccountDTO;
import Services.LoginServices;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet("/login-servlet")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String, String> errors = new HashMap<>();
        AccountDTO acc = new AccountDTO(username, password);
        boolean isVlidAccount = LoginServices.checkForLogin(acc, errors);

        if (isVlidAccount) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("Home/home.jsp");
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("Identity/login.jsp").forward(req, resp);
        }

    }

}
