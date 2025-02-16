/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DAO.AccountDAO;
import DTO.AccountDTO;
import Exceptions.AccountException;
import Utils.AccountUtils;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class AccountServices {

    public static boolean createAccountServices(AccountDTO acc, Map<String, String> errors, String type)
            throws SQLException, ClassNotFoundException, AccountException {
        boolean result = false;
        if(type.equalsIgnoreCase("member"))
            acc.setRole("member");
        else if(type.equalsIgnoreCase("admin")) {
            acc.setRole("admin");
        }
        String encryptPass = "";

        if (!AccountUtils.isValidUsername(acc.getUsername())) {
            errors.put("username", "Invalid username. Must contains at least 8 characters and dont have space.");
        }

        if (AccountUtils.isValidPassword(acc.getPassword())) {
            try {
                encryptPass = AccountUtils.EncryptPassword(acc.getPassword());
                acc.setPassword(encryptPass);
            } catch (AccountException ex) {
                errors.put("Catch Account exceptions", ex.getMessage());
                return result;
            }
        } else {
            errors.put("password", "Password is weak. It must be at least 8 characters contains: "
                    + "A-Z, "
                    + "0-9, "
                    + "A-Z, "
                    + "@#$%^&+=!");
            return result;
        }

        try {
            AccountDAO.create(acc);
        } catch (AccountException | ClassNotFoundException | SQLException e) {
            errors.put("Can not create account", e.getMessage());
            return result;
        }
        result = true;
        return result;
    }

    public static boolean isExistAccount(String username, Map<String, String> errors) {
        boolean result = true;
        int row = 0;
        try {
            row = AccountDAO.isExistAccount(username);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (row == 0) {
            result = false;
        } else {
            errors.put("username", "Username is already existed.");
        }
        return result;
    }

    public static boolean updateAccountServices(AccountDTO acc, Map<String, String> errors)
            throws SQLException, ClassNotFoundException, AccountException {
        boolean result = false;
        String encryptPass = "";

        if (!AccountUtils.isValidUsername(acc.getUsername())) {
            errors.put("username", "Invalid username. Must contains at least 8 characters and dont have space.");
        }

        boolean flag = isExistAccount(acc.getUsername(), errors);

        if (flag) {
            return result;
        }
        if (AccountUtils.isValidPassword(acc.getPassword())) {
            try {
                encryptPass = AccountUtils.EncryptPassword(acc.getPassword());
                acc.setPassword(encryptPass);
            } catch (AccountException ex) {
                errors.put("Catch Account exceptions", ex.getMessage());
                return result;
            }
        } else {
            errors.put("password", "Password is weak. It must be at least 8 characters contains: "
                    + "A-Z, "
                    + "0-9, "
                    + "A-Z, "
                    + "@#$%^&+=!");
            return result;
        }

        try {
            AccountDAO.update(acc);
        } catch (AccountException | ClassNotFoundException | SQLException e) {
            errors.put("Can not update account", e.getMessage());
            return result;
        }
        result = true;
        return result;
    }
}
