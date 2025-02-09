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
    public static AccountDTO checkForLogin(AccountDTO acc, Map<String, String> errors) {
        AccountDTO tmp = null;
        try {
            tmp = AccountDAO.getAccount(acc.getUsername());
        } catch (ClassNotFoundException | SQLException | LoginExceptions ex) {
            errors.put("Database", ex.getMessage());
            return null;
        }
        if(tmp == null) {
            errors.put("username", "Can not find user with this username.");
            return null;
        }
        
        String decodePassword = LoginUtils.decodePassword(tmp.getPassword());
        if(decodePassword.equals(acc.getPassword())) {
            return tmp;
        } else errors.put("password", "Wrong password");
        return null;
    }
}
