/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet.Identity;

import DTO.AccountDTO;
import DTO.ProfileDTO;
import Exceptions.AccountException;
import Exceptions.ProfileException;
import Services.AccountServices;
import Services.ProfileServices;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
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
 * @author DELL
 */
@WebServlet("/signup-servlet")
public class Signup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phone");
        String tmp = req.getParameter("dob");
        Date dob = Date.valueOf(tmp);
        HttpSession session = req.getSession();

        AccountDTO account = new AccountDTO(username, password);
        ProfileDTO profile = new ProfileDTO(email, firstName, lastName, phoneNumber, dob);
        Map<String, String> errors = new HashMap<>();

        boolean isExistAccount = AccountServices.isExistAccount(username, errors);
        boolean createdAccount = false;
        boolean createProfile = false;
        if (isExistAccount == false) {
            try {
                createdAccount = AccountServices.createAccountServices(account, errors);
            } catch (SQLException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, "Errors from database.", ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, "Can not find accountDTO.", ex);
            } catch (AccountException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.WARNING, "Can not create new account.", ex);
            }
        }

        if (createdAccount == true) {
            try {
                createProfile = ProfileServices.createProfileService(profile, errors, username);
            } catch (SQLException | ClassNotFoundException | ProfileException ex) {
                errors.put("Can not create profile", ex.getMessage() + " haha");
            }
        }

        errors.put("isExistAccount", "" + isExistAccount);
        errors.put("createAccount", "" + createdAccount);
        errors.put("createProfile", "" + createProfile);
        if (createProfile && createdAccount) {
            session.setAttribute("username", account.getUsername());
            session.setAttribute("id", profile.getAccountId());
            session.setAttribute("role", account.getRole());
            resp.sendRedirect("project");
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("Identity/signup.jsp").forward(req, resp);
        }
    }

}
