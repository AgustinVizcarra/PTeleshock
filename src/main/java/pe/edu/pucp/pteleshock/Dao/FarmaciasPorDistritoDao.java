package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BFarmaciaPorDistrito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FarmaciasPorDistritoDao extends BaseDao {

    public ArrayList<BFarmaciaPorDistrito> listarFarmaciasPorDistrito(int iddistrito, int inicio) {
        //msql

        ArrayList<BFarmaciaPorDistrito> listaFarmaciasPorDistrito = new ArrayList<>();

        String sql = "select u.nombre as 'Farmacias', dis.nombre as 'Distrito', f.direccion, f.idfarmacia,u.iddistrito from farmacia f\n" +
                "inner join usuario u on (f.idusuario = u.idusuario)\n" +
                "inner join distrito dis on (u.iddistrito = dis.iddistrito)\n" +
                "where dis.iddistrito = ? and f.estatus='desbloqueado' " + inicio + ",6";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, iddistrito);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BFarmaciaPorDistrito fxD = new BFarmaciaPorDistrito();
                    fxD.setNombreFarmacia(rs.getString(1));
                    fxD.setDistritoFarmacia(rs.getString(2));
                    fxD.setDireccionFarmacia(rs.getString(3));
                    fxD.setIdFarmacia(rs.getInt(4));
                    fxD.setIdDistritoF(rs.getString(5));
                    listaFarmaciasPorDistrito.add(fxD);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return listaFarmaciasPorDistrito;

    }

    public ArrayList<BFarmaciaPorDistrito> listarFarmaciasPorDistritoConBusqueda(int iddistrito, int inicio, String nombreFarmaciaBuscar) {
        //msql

        ArrayList<BFarmaciaPorDistrito> listaFarmaciasPorDistrito = new ArrayList<>();

        String sql = "select u.nombre as 'Farmacias', dis.nombre as 'Distrito', f.direccion, f.idfarmacia,u.iddistrito from farmacia f\n" +
                "inner join usuario u on (f.idusuario = u.idusuario)\n" +
                "inner join distrito dis on (u.iddistrito = dis.iddistrito)\n" +
                "where lower(u.nombre) like lower(?) and f.estatus='desbloqueado'and dis.iddistrito = ? limit " + inicio + ",6;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, "%" + nombreFarmaciaBuscar + "%");
            pstmt.setInt(2, iddistrito);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BFarmaciaPorDistrito fxD = new BFarmaciaPorDistrito();
                    fxD.setNombreFarmacia(rs.getString(1));
                    fxD.setDistritoFarmacia(rs.getString(2));
                    fxD.setDireccionFarmacia(rs.getString(3));
                    fxD.setIdFarmacia(rs.getInt(4));
                    fxD.setIdDistritoF(rs.getString(5));
                    listaFarmaciasPorDistrito.add(fxD);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return listaFarmaciasPorDistrito;

    }

    public int obtenerNumFarmacias(String iddistrito, String nombreFarmaciaBuscar) {

        int numFarm = 0;

        String sql = "select count(*) from (select f.idfarmacia, u.nombre, u.iddistrito from farmacia f inner join usuario u on (f.idusuario = u.idusuario) where iddistrito = ? and f.estatus='desbloqueado'and lower(u.nombre) like lower(?)) as `farmacias`";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, iddistrito);
            pstmt.setString(2, "%" + nombreFarmaciaBuscar + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    numFarm = rs.getInt(1);
                    return numFarm;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo hallar el numero de farmacias");

        }
        return numFarm;
    }

    public BFarmaciaPorDistrito obtenerFarmacia(int idFarmacia) {

        BFarmaciaPorDistrito farmacia=null;

        String sql = "SELECT f.idfarmacia, u.nombre,u.iddistrito,d.nombre,f.direccion FROM farmacia f \n" +
                "inner join usuario u on (f.idusuario = u.idusuario) \n" +
                "inner join distrito d on (u.iddistrito=d.iddistrito) where f.idfarmacia = ? and f.estatus='desbloqueado';";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idFarmacia);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    farmacia=new BFarmaciaPorDistrito();
                    farmacia.setIdFarmacia(rs.getInt(1));
                    farmacia.setNombreFarmacia(rs.getString(2));
                    farmacia.setIdDistritoF(rs.getString(3));
                    farmacia.setDistritoFarmacia(rs.getString(4));
                    farmacia.setDireccionFarmacia(rs.getString(5));
                    return farmacia;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo hallar la farmacias");

        }
        return farmacia;
    }
}
