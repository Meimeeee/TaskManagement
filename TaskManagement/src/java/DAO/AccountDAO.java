/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.AccountDTO;
import DAO.AccountDAO;
import Exceptions.AccountException;
import JDBC.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class AccountDAO {
    public static void create(AccountDTO acc) throws 
            SQLException, ClassNotFoundException, AccountException {
        String query = "INSERT INTO Account(username, password, role) VALUES (?, ?, ?)";
        Connection con = Connect.getConnect();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, acc.getUsername());
        stmt.setString(2, acc.getPassword());
        stmt.setString(3, acc.getRole());
        int row = stmt.executeUpdate();
        if(row == 0) 
            throw new AccountException("Can't create new account");
        
    }
}
