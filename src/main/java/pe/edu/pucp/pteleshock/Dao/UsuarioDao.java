package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.BDistristos;
import pe.edu.pucp.pteleshock.Beans.BRol;
import pe.edu.pucp.pteleshock.Beans.BUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao extends BaseDao{

    public BUsuario validarUsuarioPassword(String correo, String password){

        BUsuario usuario = null;

        String sql = "Select * from usuario where correo = ? and contrasenia = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setString(1,correo);
            pstmt.setString(2,password);

            try (ResultSet rs = pstmt.executeQuery();){
                if (rs.next()){
                    int idUsuario = rs.getInt(1);
                    usuario = this.obtenerUsuario(idUsuario);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;

    }

    public BUsuario obtenerUsuario(int idUsuario){
        BUsuario usuario = null;

        String sql = "Select * from usuario where idusuario = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setInt(1,idUsuario);

            try (ResultSet rs = pstmt.executeQuery();){
                if (rs.next()){
                    usuario = new BUsuario();

                    usuario.setIdUsuario(idUsuario);

                    BRol rol = new BRol();
                    rol.setIdRol(rs.getInt(2));
                    usuario.setRol(rol);

                    BDistristos distrito = new BDistristos();
                    distrito.setIdDistrito(rs.getInt(3));
                    usuario.setDistritos(distrito);

                    usuario.setNombre(rs.getString(4));
                    usuario.setApellido(rs.getString(5));
                    usuario.setCorreo(rs.getString(6));
                    usuario.setDni(rs.getString(7));
                    usuario.setContrasenia(rs.getString(9));

                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;
    }

}
