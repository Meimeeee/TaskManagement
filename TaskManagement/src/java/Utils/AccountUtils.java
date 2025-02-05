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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountUtils {

    public static String EncryptPassword(String password) throws AccountException {
        // Check conditions of the password
        if (password == null || password.isEmpty()) {
            throw new AccountException("Password can't be empty");
        }
        if (!validatePassword(password)) {
            throw new AccountException("Password is weak. Password must contains at least 1 uppercase"
                    + ", 1 character, 1 special character, 1 number.");
        }

        // Encode the string into Base64
        password = Base64.getEncoder().encodeToString(password.getBytes());
        
        return password;
    }
    
    public static void decodePassword(String password) {
        
        // Decode the Base64 string
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        
        // Convert byte array back to string
        String decodedString = new String(decodedBytes);
        
        password = decodedString;
    }

    public static boolean validatePassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        // Check password with regex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        // If password is valid, return true, else return false
        return matcher.matches();
    }
}
