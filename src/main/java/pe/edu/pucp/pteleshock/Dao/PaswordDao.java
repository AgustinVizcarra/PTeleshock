package pe.edu.pucp.pteleshock.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PaswordDao extends BaseDao{
    public void cambiarContra(String nombre_usuario,String pwd){
        String sql = "update usuario set contrasenia= ? where nombre= ?";
        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1,pwd);
            preparedStatement.setString(2,nombre_usuario);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
