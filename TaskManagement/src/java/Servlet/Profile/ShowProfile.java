/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet.Profile;

import DTO.ProfileDTO;
import Exceptions.ProfileException;
import Services.ProfileServices;
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
 * @author DELL
 */
@WebServlet("/show-profile")
public class ShowProfile extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer id = (Integer) session.getAttribute("id");
        ProfileDTO profile = new ProfileDTO();
        profile.setAccountId(id);
        try {
            profile = ProfileServices.showProfileServices(id);
        } catch (SQLException | ClassNotFoundException | ProfileException ex) {
            Logger.getLogger(ShowProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("email", profile.getEmail());
        req.setAttribute("firstName", profile.getFirstName());
        req.setAttribute("lastName", profile.getLastName());
        req.setAttribute("phoneNumber", profile.getPhoneNumber());
        req.setAttribute("dob", profile.getDateOfBirth().toString());
        
        req.getRequestDispatcher("Profile/ShowProfile.jsp").forward(req, resp);
    }
    
}
