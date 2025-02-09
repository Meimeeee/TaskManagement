/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DAO.AccountDAO;
import DAO.ProfileDAO;
import DTO.ProfileDTO;
import Exceptions.AccountException;
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

        // Validate email
        if (!ProfileUtils.isValidEmail(profile.getEmail())) {
            errors.put("email", "Invalid email.");
        }

        // Validate first name
        if (!ProfileUtils.isValidName(profile.getFirstName())) {
            errors.put("firstName", "Invalid first name.");
        }

        // Validate last name
        if (!ProfileUtils.isValidName(profile.getLastName())) {
            errors.put("lastName", "Invalid last name.");
        }

        // Validate phone number
        if (!ProfileUtils.isValidPhone(profile.getPhoneNumber())) {
            errors.put("phoneNumber", "Invalid phone number.");
        }
        
        if(errors.size() > 0)
            return result;

        // Lấy ID của tài khoản
        try {
            Integer id = AccountDAO.getIdByUsername(username);
            if(id != null) {
                errors.put("accountID", "" + id);
                profile.setAccountId(id);
            } else errors.put("accountId", "null");
        } catch (SQLException | ClassNotFoundException e) {
            errors.put("Can not create profile", e.getMessage() + " khi lay ID");
        }

        // Thử tạo profile, bắt ngoại lệ nếu có
        try {
            ProfileDAO.create(profile);
        } catch (ProfileException | ClassNotFoundException | SQLException e) {
            errors.put("database", "Failed to create profile: " + e.getMessage() + " khi create ");
            return result;
        }
        result = true;
        return result;
    }
}
