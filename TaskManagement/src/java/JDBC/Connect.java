/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngoct
 */
public class Connect {

    private static final String username = "sa";
    private static final String password = "123123123";
    private static final String databaseName = "TaskManagement";
    private static final String url = "jdbc:sqlserver://localhost:1433" + ";databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true";
    
    public static Connection getConnect() throws ClassNotFoundException, SQLException {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
