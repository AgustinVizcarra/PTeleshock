package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.BDistristos;
import pe.edu.pucp.pteleshock.Beans.BRol;
import pe.edu.pucp.pteleshock.Beans.BUsuario;

import java.sql.*;

public class UsuarioDao extends BaseDao {

    public BUsuario validarUsuarioPassword(String correo, String password) {

        BUsuario usuario = null;

        String sql = "Select * from usuario where correo = ? and contrasenia = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, correo);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);
                    usuario = this.obtenerUsuario(idUsuario);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;

    }

    public BUsuario obtenerUsuario(int idUsuario) {
        BUsuario usuario = null;

        String sql = "Select * from usuario where idusuario = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    usuario = new BUsuario();

                    usuario.setIdUsuario(idUsuario);
                    usuario.setIdRol(rs.getInt(2));
                    usuario.setIdDistrito(rs.getInt(3));
                    usuario.setNombre(rs.getString(4));
                    usuario.setApellido(rs.getString(5));
                    usuario.setCorreo(rs.getString(6));
                    usuario.setDni(rs.getString(7));
                    usuario.setDni(rs.getString(7));
                    usuario.setRuc(rs.getString(8));
                    usuario.setPwd(rs.getString(9));

                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;
    }

    public boolean farmaciaBloqueada(int id_farmacia){
        boolean estaBloqueada = false;
        String sql = "select * from usuario u inner join farmacia f on (f.idusuario=u.idusuario) where u.idusuario="+id_farmacia+" and f.estatus = \"bloqueado\" ";
        try(Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){
            if(rs.next()){
                estaBloqueada=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estaBloqueada;
    }

}
