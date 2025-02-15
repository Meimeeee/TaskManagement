/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet.Account;

import DTO.AccountDTO;
import Services.AccountServices;
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
@WebServlet("/update-account")
public class UpdateAccount extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        
        AccountDTO account = new AccountDTO(username, password);
        Map<String, String> errors = new HashMap<>();
        Integer id = (Integer) session.getAttribute("id");
        account.setAccountId(id);
        
        boolean updated = AccountServices.updateAccountServices(account, errors);
        if(updated) {
            session.setAttribute("username", account.getUsername());
            resp.sendRedirect(req.getContextPath() + "/project");
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("Account/EditAccount.jsp").forward(req, resp);
        }
        
    }
    
}
