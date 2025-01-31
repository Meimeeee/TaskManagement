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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class AccountServices {
    public static boolean createAccountServices(AccountDTO acc) 
            throws SQLException, ClassNotFoundException, AccountException {
        boolean result = false;
        
        String encryptPass = "";
        try {
            encryptPass = AccountUtils.EncryptPassword(acc.getPassword());
        } catch (AccountException ex) {
            Logger.getLogger(AccountServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        acc.setPassword(encryptPass);
        acc.setRole("TeamMember");
        AccountDAO.create(acc);
        
        result = true;
        
        return result;
    }
}
