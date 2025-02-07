/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet.Identity;

import DTO.AccountDTO;
import DTO.ProfileDTO;
import Exceptions.ProfileException;
import Services.ProfileServices;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@WebServlet("/signup-servlet")
public class Signup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phone");
        String tmp = req.getParameter("dob");
        LocalDate dob = LocalDate.parse(tmp);

        ProfileDTO profile = new ProfileDTO(email, firstName, lastName, phoneNumber, dob);
        Map<String, String> errors = new HashMap<>();
        try {
            boolean result = ProfileServices.createProfileService(profile, errors);
        } catch (SQLException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProfileException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("SignUp/signup.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("home").forward(req, resp);
        }

    }

}
