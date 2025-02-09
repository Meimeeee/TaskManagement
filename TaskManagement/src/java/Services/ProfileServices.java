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
        int count = 0;

        // Validate email
        if (!ProfileUtils.isValidEmail(profile.getEmail())) {
            errors.put("email", "Invalid email.");
            count++;
        }

        // Validate first name
        if (!ProfileUtils.isValidName(profile.getFirstName())) {
            errors.put("firstName", "Invalid first name.");
            count++;
        }

        // Validate last name
        if (!ProfileUtils.isValidName(profile.getLastName())) {
            errors.put("lastName", "Invalid last name.");
            count++;
        }

        // Validate phone number
        if (!ProfileUtils.isValidPhone(profile.getPhoneNumber())) {
            errors.put("phoneNumber", "Invalid phone number.");
            count++;
        }

        if (!errors.isEmpty()) {
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
            errors.put("Profile srevices", "Before try to create profile");
            ProfileDAO.create(profile);
            errors.put("ProfileServices", "Try after to create profile : works");
            result = true;
        } catch (ProfileException | ClassNotFoundException | SQLException e) {
            errors.put("database", "Failed to create profile: " + e.getMessage() + " khi create ");
            return result;
        }
        return result;
    }
}
