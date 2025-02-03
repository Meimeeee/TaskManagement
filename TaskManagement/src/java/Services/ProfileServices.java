/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

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
    public static boolean createProfileService(ProfileDTO profile, Map<String, String> errors) 
            throws SQLException, ClassNotFoundException, ProfileException {
        boolean result = false;
        String err = "";
        
        // Check email 
        boolean isValidEmail = ProfileUtils.isValidEmail(profile.getEmail());
        if(!isValidEmail)
            errors.put("email", "Invalid email.");
        
        // Check first name
        boolean isValidName = ProfileUtils.isValidName(profile.getFirstName());
        if(!isValidEmail)
            errors.put("firstName", "Invalid last name.");
        
        // Check last name
        isValidName = ProfileUtils.isValidName(profile.getLastName());
        if(!isValidEmail)
            errors.put("lastName", "Invalid last name.");
        
        // Check phone 
        boolean isValidPhone = ProfileUtils.isValidPhone(profile.getPhoneNumber());
        if(!isValidPhone)
            errors.put("phoneNumber", "Invalid phone number.");

        if(!err.equalsIgnoreCase(""))
            throw new ProfileException(err);
        
        result = true;
        return result;
    }
}
