package pe.edu.pucp.pteleshock.Dao;
import pe.edu.pucp.pteleshock.Beans.BDistristos;
import pe.edu.pucp.pteleshock.Beans.BCliente;

import java.sql.*;


public class PerfilClientDao extends BaseDao {

    public BCliente clientePerfil(int idCliente){

        BCliente bCliente = null;

        String sql1 = "SELECT u.idusuario, u.nombre, u.apellido, u.correo, u.dni, d.nombre, d.iddistrito as 'Distrito' FROM usuario u\n" +
                "inner join distrito d on (u.iddistrito = d.iddistrito)\n" +
                "WHERE u.idusuario = ?; ";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql1);) {
            pstmt.setInt(1, idCliente);
            try( ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    bCliente = new BCliente();
                    bCliente.setIdCliente(rs.getInt(1));
                    bCliente.setNombre(rs.getString(2));
                    bCliente.setApellido(rs.getString(3));
                    bCliente.setCorreo(rs.getString(4));
                    bCliente.setDni(rs.getString(5));
                    BDistristos bDistristos = new BDistristos();
                    bDistristos.setNombre(rs.getString(6));
                    bDistristos.setIdDistrito(rs.getInt(7));
                    bCliente.setDistrito(bDistristos);
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bCliente;
    }



    public void editarCliente(int idCliente,String nombre, String apellido, int idDis) {
        String sql ="UPDATE usuario SET nombre=?,apellido=?,iddistrito=? WHERE (idusuario=?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setInt(3, idDis);
            pstmt.setInt(4, idCliente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void elimLogicoCliente(int idCliente){
        String sql ="UPDATE usuario SET elimLogico='deshabilitado' WHERE (idusuario=? and idrol=1);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






}
