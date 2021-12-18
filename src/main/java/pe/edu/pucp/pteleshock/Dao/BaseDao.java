package pe.edu.pucp.pteleshock.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class BaseDao {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
        String user = "root";
        String pass = "root";
        String option = "mydb";
        String option2 = "teleshock_bd_oficial";
        String url = "jdbc:mysql://localhost:3306/"+option+"?serverTimezone=America/Lima";
        return DriverManager.getConnection(url,user,pass);
    }
}
