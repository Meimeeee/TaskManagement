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
        AccountDTO tmp = new AccountDTO();
        try {
            tmp = AccountDAO.getAccount(acc.getUsername());
        } catch (ClassNotFoundException | SQLException | LoginExceptions ex) {
            return null;
        }

        if (tmp != null) {
            String decodePassword = LoginUtils.decodePassword(tmp.getPassword());
            if (decodePassword.equals(acc.getPassword())) {
                return tmp;
            } else {
                errors.put("password", "Wrong password");
                return null;
            }
        } else {
            errors.put("username", "Username is not exist.");
            return null;
        }
    }
}
