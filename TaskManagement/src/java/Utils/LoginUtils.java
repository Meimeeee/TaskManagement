/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.Base64;

/**
 *
 * @author DELL
 */
public class LoginUtils {

    public static String decodePassword(String password) {

        // Decode the Base64 string
        byte[] decodedBytes = Base64.getDecoder().decode(password);

        // Convert byte array back to string
        String decodedString = new String(decodedBytes);

        return decodedString;
    }
}
