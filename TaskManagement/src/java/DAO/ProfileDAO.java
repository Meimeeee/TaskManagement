/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ProfileDTO;
import Exceptions.ProfileException;
import JDBC.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class ProfileDAO {

    public static void create(ProfileDTO profile)
            throws SQLException, ProfileException, ClassNotFoundException {
        String query = "INSERT INTO profile(account_id, email, first_name, last_name, phone_number, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Connect.getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, profile.getAccountId());
            stmt.setString(2, profile.getEmail());
            stmt.setString(3, profile.getFirstName());
            stmt.setString(4, profile.getLastName());
            stmt.setString(5, profile.getPhoneNumber());
            stmt.setDate(6, profile.getDateOfBirth());

            int row = stmt.executeUpdate();
            if (row == 0) {
                throw new ProfileException("Failed to insert profile.");
            }
        }                
    }
    
    public static void update(ProfileDTO profile)
            throws SQLException, ProfileException, ClassNotFoundException {
        String query = "UPDATE profile SET email = ?, first_name = ?, last_name = ?, phone_number = ?, date_of_birth = ? WHERE account_id = ?";

        try (Connection con = Connect.getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, profile.getEmail());
            stmt.setString(2, profile.getFirstName());
            stmt.setString(3, profile.getLastName());
            stmt.setString(4, profile.getPhoneNumber());
            stmt.setDate(5, profile.getDateOfBirth());
            stmt.setInt(6, profile.getAccountId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new ProfileException("No profile updated. Account ID may not exist.");
            }

        }
    }

    public static ProfileDTO show(Integer id)
            throws SQLException, ProfileException, ClassNotFoundException {
        String query = "SELECT * FROM profile WHERE account_id = ?";
        ProfileDTO profile = new ProfileDTO();

        try (Connection con = Connect.getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profile.setEmail(rs.getString("email"));
                    profile.setFirstName(rs.getString("first_name"));
                    profile.setLastName(rs.getString("last_name"));
                    profile.setPhoneNumber(rs.getString("phone_number"));
                    profile.setDateOfBirth(rs.getDate("date_of_birth"));
                    
                } else {
                    profile = null;
                }
            }
        }
        return profile;
    }
}
