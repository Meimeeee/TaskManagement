/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author DELL
 */
public class ProfileUtils {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String NAME_REGEX = "^(?:[A-Za-z]{3,})(?: [A-Za-z]{3,})*$";
    private static final String PHONE_REGEX = "^0\\d{9,10}$";
    
    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
    
    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }
    
    public static boolean isValidPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }
}
