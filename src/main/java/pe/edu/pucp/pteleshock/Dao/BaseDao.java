package pe.edu.pucp.pteleshock.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        return DriverManager.getConnection(url,user,pass);
    }
}
