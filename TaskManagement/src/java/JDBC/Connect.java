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

    private static Connection connect;

    public static Connection getConnect() throws SQLException, ClassNotFoundException {
        if (connect == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String username = "sa";
                String pass = "12345";
                String DB_NAME = "TaskManagement";
                String URL = "jdbc:sqlserver://localhost:1433"
                        + ";databaseName=" + DB_NAME
                        + ";trustServerCertificate=true";

                Connection connectDriver = DriverManager.getConnection(URL, username, pass);
                connect = connectDriver;
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
                throw new SQLException("Can't connect DB");
            }
        }
        return connect;
    }
}
