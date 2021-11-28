package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.BDistristos;
import pe.edu.pucp.pteleshock.Beans.BRol;
import pe.edu.pucp.pteleshock.Beans.BUsuarioCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioClienteDao extends BaseDao{

    public BUsuarioCliente validarClientePassword (String correo, String password){

        BUsuarioCliente cliente = null;

        String sql = "Select * from usuario where correo = ? and contrasenia = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setString(1,correo);
            pstmt.setString(2,password);

            try (ResultSet rs = pstmt.executeQuery();){
                if (rs.next()){
                    int idCiente = rs.getInt(1);
                    cliente = this.obtenerCliente(idCiente);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cliente;

    }

    public BUsuarioCliente obtenerCliente (int idCliente){
        BUsuarioCliente cliente = null;

        String sql = "Select * from usuario where idusuario = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setInt(1,idCliente);

            try (ResultSet rs = pstmt.executeQuery();){
                if (rs.next()){
                    cliente = new BUsuarioCliente();

                    BRol rol = new BRol();
                    rol.setIdRol(rs.getInt(2));
                    cliente.setRol(rol);

                    BDistristos distrito = new BDistristos();
                    distrito.setIdDistrito(rs.getInt(3));
                    cliente.setDistritos(distrito);

                    cliente.setNombre(rs.getString(4));
                    cliente.setApellido(rs.getString(5));
                    cliente.setCorreo(rs.getString(6));
                    cliente.setDni(rs.getString(7));
                    cliente.setContrasenia(rs.getString(9));

                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cliente;
    }

}
