/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DAO.AccountDAO;
import DAO.ProfileDAO;
import DTO.ProfileDTO;
import Exceptions.ProfileException;
import Utils.ProfileUtils;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ProfileServices {

    public static boolean createProfileService(ProfileDTO profile, Map<String, String> errors, String username)
            throws SQLException, ClassNotFoundException, ProfileException {
        boolean result = false;
        boolean flag = false; 

        // Validate email
        if (!ProfileUtils.isValidEmail(profile.getEmail())) {
            errors.put("email", "Invalid email.");
            flag = true;
        }

        // Validate first name
        if (!ProfileUtils.isValidName(profile.getFirstName())) {
            errors.put("firstName", "Invalid first name.");
            flag = true;
        }

        // Validate last name
        if (!ProfileUtils.isValidName(profile.getLastName())) {
            errors.put("lastName", "Invalid last name.");
            flag = true;
        }

        // Validate phone number
        if (!ProfileUtils.isValidPhone(profile.getPhoneNumber())) {
            errors.put("phoneNumber", "Invalid phone number.");
            flag = true;
        }

        if (flag) {
            return result;
        }

        // Get account ID
        try {
            Integer id = AccountDAO.getIdByUsername(username);
            profile.setAccountId(id);
        } catch (SQLException | ClassNotFoundException e) {
            errors.put("Can not create profile", e.getMessage() + " khi lay ID");
        }

        // Create profile
        try {
            ProfileDAO.create(profile);
            result = true;
        } catch (ProfileException | ClassNotFoundException | SQLException e) {
            errors.put("database", "Failed to create profile: " + e.getMessage() + " khi create ");
            return result;
        }
        return result;
    }
    
    public static boolean updateProfileServices(ProfileDTO profile, Map<String, String> errors)
            throws SQLException, ClassNotFoundException, ProfileException {
        boolean result = false;
        boolean flag = false; 

        // Validate email
        if (!ProfileUtils.isValidEmail(profile.getEmail())) {
            errors.put("email", "Invalid email.");
            flag = true;
        }

        // Validate first name
        if (!ProfileUtils.isValidName(profile.getFirstName())) {
            errors.put("firstName", "Invalid first name.");
            flag = true;
        }

        // Validate last name
        if (!ProfileUtils.isValidName(profile.getLastName())) {
            errors.put("lastName", "Invalid last name.");
            flag = true;
        }

        // Validate phone number
        if (!ProfileUtils.isValidPhone(profile.getPhoneNumber())) {
            errors.put("phoneNumber", "Invalid phone number.");
            flag = true;
        }

        if (flag) {
            return result;
        }

        // Create profile
        try {
            ProfileDAO.update(profile);
            result = true;
        } catch (ProfileException | ClassNotFoundException | SQLException e) {
            errors.put("database", "Failed to update profile: " + e.getMessage());
            return result;
        }
        return result;
    }
    
     public static ProfileDTO showProfileServices(Integer id)
            throws SQLException, ClassNotFoundException, ProfileException {
        ProfileDTO profile = ProfileDAO.show(id);
        return profile;
    }
}
