package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.BLbloqueadas;

import java.sql.*;
import java.util.ArrayList;

public class ValidacionUnl_Dao extends BaseDao {
    public ArrayList<BLbloqueadas> listarBloqueada(String pag) {
        int pagint = (Integer.parseInt(pag) - 1) * 5;
        ArrayList<BLbloqueadas> bLbloqueadas = new ArrayList<>();
        String sql = "select u.nombre,u.ruc,f.idfarmacia from usuario u inner join farmacia f on (f.idusuario=u.idusuario) where u.idrol=3 and f.estatus='bloqueado'\n" +
                "limit ?,5;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pagint);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bLbloqueadas.add(new BLbloqueadas(rs.getInt(3), rs.getString(1), rs.getString(2)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bLbloqueadas;
    }

    public int cantidadListarBloqueadas() {
        ArrayList<BLbloqueadas> bLbloqueadas = new ArrayList<>();
        String sql = "select u.nombre,u.ruc,f.idfarmacia from usuario u inner join farmacia f on (f.idusuario=u.idusuario) where u.idrol=3 and f.estatus='bloqueado';";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bLbloqueadas.add(new BLbloqueadas(rs.getInt(3), rs.getString(1), rs.getString(2)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bLbloqueadas.size();
    }

    public void cambiarEstado(int idfarmacia) {
        String sql = "update farmacia set estatus='desbloqueado' where idfarmacia=?";
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, idfarmacia);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void registrohistorial(int idfarmacia, String admin, String razon) {
        int admin_id = 0;
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery("Select idusuario from usuario u where u.nombre='" + admin + "';");
            while (rs.next()) {
                admin_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "insert into historialadmin (idusuario, idfarmacia, idaccion, razon, fechahora) values(?,?,?,?,now())";
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, admin_id);
            preparedStatement.setInt(2, idfarmacia);
            preparedStatement.setString(3, "2");
            preparedStatement.setString(4, razon);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error en el registro de desbloquear farmacia como historial");
        }

    }
}
