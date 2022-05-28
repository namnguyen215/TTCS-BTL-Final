package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/Luxstay";
            String username = "root";
            String password = "Nguyennam6626@";
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
