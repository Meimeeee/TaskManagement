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

            int row = stmt.executeUpdate();
            if (row == 0) {
                throw new ProfileException("Failed to update profile.");
            }
        }
    }

    public static ProfileDTO show(Integer id)
            throws SQLException, ProfileException, ClassNotFoundException {
        ProfileDTO profile = new ProfileDTO();
        String query = "SELECT * FROM profile WHERE account_id = ?";
        try (Connection con = Connect.getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id); 
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profile.setEmail(rs.getString("email") != null ? rs.getString("email") : "Not yet. Please insert!");
                    profile.setFirstName(rs.getString("first_name") != null ? rs.getString("first_name") : "Not yet. Please insert!");
                    profile.setLastName(rs.getString("last_name") != null ? rs.getString("last_name") : "Not yet. Please insert!");
                    profile.setPhoneNumber(rs.getString("phone_number") != null ? rs.getString("phone_number") : "Not yet. Please insert!");
                    profile.setDateOfBirth(rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth") : new java.sql.Date(System.currentTimeMillis()));
                } else {
                    throw new ProfileException("Profile not found for account_id: " + profile.getAccountId());
                }
            }
        }
        return profile;
    }
}
