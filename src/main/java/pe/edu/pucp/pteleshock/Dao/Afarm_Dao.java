package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BAnadidas;

import java.sql.*;
import java.util.ArrayList;

public class Afarm_Dao extends BaseDao {

    public ArrayList<BAnadidas> listar_anadidas(String pag) {
        ArrayList<BAnadidas> lista_farmacia_anadidas = new ArrayList<>();
        int pagint = (Integer.parseInt(pag)-1)*7;

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.nombre 'Administrador',farm.nombre 'Farmacia' ,farm.ruc as 'RUC' ,historialadmin.fechahora 'Fecha' from historialadmin\n" +
                     "inner join farmacia f on (historialadmin.idfarmacia = f.idfarmacia)\n" +
                     "inner join usuario a on (a.idusuario=historialadmin.idusuario)\n" +
                     "inner join accion on (accion.idaccion=historialadmin.idaccion)\n" +
                     "inner join usuario farm on (farm.idusuario=f.idusuario)\n" +
                     "where accion.idaccion=3 \n" +
                     "order by historialadmin.fechahora desc\n" +
                     "limit ?,7;")) {

            pstmt.setInt(1, pagint);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BAnadidas farmacia = new BAnadidas();
                    farmacia.setAdministrador_nombre(rs.getString(1));
                    farmacia.setFarmacia_nombre(rs.getString(2));
                    farmacia.setRuc(rs.getString(3));
                    farmacia.setFecha(rs.getString(4));
                    lista_farmacia_anadidas.add(farmacia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al listar historial de FAñadidas con paginacion");
        }
        return lista_farmacia_anadidas;
    }

    public int cantidadListaAnadidas() {
        ArrayList<BAnadidas> lista_farmacia_anadidas = new ArrayList<>();
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.nombre 'Administrador',farm.nombre 'Farmacia' ,farm.ruc as 'RUC' ,historialadmin.fechahora 'Fecha' from historialadmin\n" +
                     "inner join farmacia f on (historialadmin.idfarmacia = f.idfarmacia)\n" +
                     "inner join usuario a on (a.idusuario=historialadmin.idusuario)\n" +
                     "inner join accion on (accion.idaccion=historialadmin.idaccion)\n" +
                     "inner join usuario farm on (farm.idusuario=f.idusuario)\n" +
                     "where accion.idaccion=3 \n" +
                     "order by historialadmin.fechahora desc;")) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BAnadidas farmacia = new BAnadidas();
                    farmacia.setAdministrador_nombre(rs.getString(1));
                    farmacia.setFarmacia_nombre(rs.getString(2));
                    farmacia.setRuc(rs.getString(3));
                    farmacia.setFecha(rs.getString(4));
                    lista_farmacia_anadidas.add(farmacia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al listar historial de FAñadidas");
        }
        return lista_farmacia_anadidas.size();
    }
}
