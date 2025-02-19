/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet.Account;

import DTO.AccountDTO;
import Exceptions.AccountException;
import Services.AccountServices;
import Services.ProfileServices;
import Servlet.Identity.Signup;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet("/create-account")
public class CreateAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        AccountDTO account = new AccountDTO(username, password);
        Map<String, String> errors = new HashMap<>();
        boolean isExistAccount = AccountServices.isExistAccount(username, errors);
        boolean createdAccount = false;
        boolean createdProfile = false;
        if (isExistAccount == false) {
            try {
                createdAccount = AccountServices.createAccountServices(account, errors, "admin");
            } catch (SQLException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, "Errors from database.", ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, "Can not find accountDTO.", ex);
            } catch (AccountException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.WARNING, "Can not create new account.", ex);
            }
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("Account/CreateAccount.jsp").forward(req, resp);
        }

        if (createdAccount) {
            createdProfile = ProfileServices.createManagerAccountServices(username);
        }

        if (createdAccount && createdProfile) {
            resp.sendRedirect("project");
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("Account/CreateAccount.jsp").forward(req, resp);
        }

    }

}
