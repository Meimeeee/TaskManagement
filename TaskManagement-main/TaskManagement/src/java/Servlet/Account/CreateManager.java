/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet.Account;

import DTO.AccountDTO;
import Exceptions.AccountException;
import Services.AccountServices;
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
@WebServlet("/create-manager")
public class CreateManager extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        AccountDTO account = new AccountDTO(username, password);
        Map<String, String> errors = new HashMap<>();
        boolean createdAccount = false;
        try {
            createdAccount = AccountServices.createAccountServices(account, errors, 1);
        } catch (SQLException ex) {
            Logger.getLogger(CreateManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccountException ex) {
            Logger.getLogger(CreateManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(createdAccount) {
           resp.sendRedirect(req.getContextPath() + "/project");
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("Account/CreateManager.jsp").forward(req, resp);
        }
    }
    
}
