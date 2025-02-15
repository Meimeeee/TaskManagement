/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlet.Profile;

import DTO.ProfileDTO;
import Exceptions.ProfileException;
import Services.ProfileServices;
import java.io.IOException;
import java.sql.Date;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet("/update-profile")
public class UpdateProfile extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        String tmp = req.getParameter("dob");
        Date dob = Date.valueOf(tmp);
        HttpSession session = req.getSession();
        Integer id = (Integer) session.getAttribute("id");
        ProfileDTO profile = new ProfileDTO(email, firstName, lastName, phoneNumber, dob);
        profile.setAccountId(id);
        Map<String, String> errors = new HashMap<>();
        
        boolean updated = false;
        try {
            updated = ProfileServices.updateProfileServices(profile, errors);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProfileException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(updated) {
            resp.sendRedirect(req.getContextPath() + "/project");
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("Profile/EditProfile.jsp").forward(req, resp);
        }
    }
    
}
