package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.Bdesbloqueadas;

import java.sql.*;
import java.util.ArrayList;

public class Dfarm_Dao extends BaseDao {
    public ArrayList<Bdesbloqueadas> listar_desbloqueadas(String pag) {
        ArrayList<Bdesbloqueadas> bdesbloqueadas = new ArrayList<>();
        int pagint = (Integer.parseInt(pag) - 1) * 7;

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.nombre 'Administrador',farm.nombre 'Farmacia' ,historialadmin.razon 'Razon/Motivo' ,historialadmin.fechahora 'Fecha' from historialadmin \n" +
                     "inner join farmacia f on (historialadmin.idfarmacia = f.idfarmacia)\n" +
                     "inner join usuario a on (a.idusuario=historialadmin.idusuario)\n" +
                     "inner join accion on (accion.idaccion=historialadmin.idaccion)\n" +
                     "inner join usuario farm on (farm.idusuario=f.idusuario)\n" +
                     "where accion.idaccion=2\n" +
                     "order by historialadmin.fechahora desc\n" +
                     "limit ?,7;")) {

            pstmt.setInt(1, pagint);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Bdesbloqueadas desbloqueada = new Bdesbloqueadas();
                    desbloqueada.setAdministrador_nombre(rs.getString(1));
                    desbloqueada.setFarmacia_nombre(rs.getString(2));
                    desbloqueada.setRazon_motivo(rs.getString(3));
                    desbloqueada.setFecha(rs.getString(4));
                    bdesbloqueadas.add(desbloqueada);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bdesbloqueadas;
    }

    public int cantidadListaDesbloquedas() {
        ArrayList<Bdesbloqueadas> bdesbloqueadas = new ArrayList<>();

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.nombre 'Administrador',farm.nombre 'Farmacia' ,historialadmin.razon 'Razon/Motivo' ,historialadmin.fechahora 'Fecha' from historialadmin \n" +
                     "inner join farmacia f on (historialadmin.idfarmacia = f.idfarmacia)\n" +
                     "inner join usuario a on (a.idusuario=historialadmin.idusuario)\n" +
                     "inner join accion on (accion.idaccion=historialadmin.idaccion)\n" +
                     "inner join usuario farm on (farm.idusuario=f.idusuario)\n" +
                     "where accion.idaccion=2\n" +
                     "order by historialadmin.fechahora desc;")) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Bdesbloqueadas desbloqueada = new Bdesbloqueadas();
                    desbloqueada.setAdministrador_nombre(rs.getString(1));
                    desbloqueada.setFarmacia_nombre(rs.getString(2));
                    desbloqueada.setRazon_motivo(rs.getString(3));
                    desbloqueada.setFecha(rs.getString(4));
                    bdesbloqueadas.add(desbloqueada);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bdesbloqueadas.size();
    }

}