/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author DELL
 */
import Exceptions.AccountException;
import java.util.Base64;

public class AccountUtils {
    private static String USERNAME_REGEX = "[A-Za-z]{8,}";
    private static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    public static String EncryptPassword(String password) throws AccountException {
        // Check conditions of the password
        if (password == null || password.isEmpty()) {
            throw new AccountException("Password can't be empty");
        }
        if (!isValidPassword(password)) {
            throw new AccountException("Password is weak.");
        }

        // Encode the string into Base64
        password = Base64.getEncoder().encodeToString(password.getBytes());

        return password;
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
    
    public static boolean isValidUsername(String username) {
        return username.matches(USERNAME_REGEX);
    }
    
    

}
