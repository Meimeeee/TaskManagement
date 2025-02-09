/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DAO.AccountDAO;
import DTO.AccountDTO;
import Exceptions.LoginExceptions;
import Utils.LoginUtils;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class LoginServices {
    public static boolean checkForLogin(AccountDTO acc, Map<String, String> errors) {
        boolean result = false;
        String password = "";
        try {
            password = AccountDAO.getPasswordByUsername(acc.getUsername());
        } catch (ClassNotFoundException | SQLException | LoginExceptions ex) {
            errors.put("Database", ex.getMessage());
        }
        if(password.equalsIgnoreCase("")) {
            errors.put("username", "Can not find user with this username.");
            return result;
        }
        
        String decodePassword = LoginUtils.decodePassword(password);
        errors.put("Wrong pass", decodePassword);
        if(decodePassword.equals(acc.getPassword())) {
            result = true;
        } else errors.put("password", "Wrong password");
        return result;
    }
}
