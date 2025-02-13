/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.AccountDTO;
import Exceptions.AccountException;
import Exceptions.LoginExceptions;
import JDBC.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class AccountDAO {

    public static void create(AccountDTO acc) throws
            SQLException, ClassNotFoundException, AccountException {
        String query = "INSERT INTO account(username, password, role) VALUES (?, ?, ?)";
        try (Connection con = Connect.getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, acc.getUsername());
            stmt.setString(2, acc.getPassword());
            stmt.setString(3, acc.getRole());
            int row = stmt.executeUpdate();
            if (row == 0) {
                throw new AccountException("Can not create new account.");
            }
        }
    }

    public static List<AccountDTO> getAllAccounts()
            throws ClassNotFoundException, SQLException {
        List<AccountDTO> list = new ArrayList<>();
        String query = "SELECT * FROM account";
        try (Connection con = Connect.getConnect(); PreparedStatement stmt = con.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AccountDTO account = new AccountDTO();
                account.setAccountId(rs.getInt("account_id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                account.setCreateAt(rs.getDate("create_at"));
                account.setUpdateAt(rs.getDate("update_at"));
                list.add(account);
            }
        }
        return list;
    }

    public static Integer getIdByUsername(String username)
            throws ClassNotFoundException, SQLException {
        String query = "SELECT account_id FROM account WHERE username = ?";
        int result = 0;
        try (Connection con = Connect.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("account_id");
            }
        }
        return result;
    }

    public static int isExistAccount(String username)
            throws ClassNotFoundException, SQLException {
        String query = "SELECT COUNT(*) FROM account WHERE username = ?";
        int result = 0;
        try (Connection con = Connect.getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        }
        return result;
    }

    public static AccountDTO getAccount(String username)
            throws ClassNotFoundException, SQLException, LoginExceptions {
        String query = "SELECT * FROM account WHERE username = ? ";
        AccountDTO ans = null;
        try (Connection con = Connect.getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("username");
                    String pass = rs.getString("password");
                    Integer id = rs.getInt("account_id");
                    String role = rs.getString("role");
                    ans = new AccountDTO(id, username, pass, role);
                } else {
                    throw new LoginExceptions("Can not find user by this username");
                }
            }
        }
        return ans;
    }
}
