package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BDetProd;
import pe.edu.pucp.pteleshock.Beans.BFarmaciaPorDistrito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosFDao extends BaseDao{

    public ArrayList<BDetProd> listarProductosF(int idFarmacia, int inicio) {
        //msql

        ArrayList<BDetProd> listarProductosF = new ArrayList<>();


        String sql = "Select  p.idproducto,p.nombre, f.foto1,pf.stock,pf.preciounitario,pf.recetamedica from productoporfarmacia pf\n" +
                "inner join producto p on (pf.idproducto=p.idproducto)\n" +
                "left join foto f on (pf.idproducto=f.idproducto and pf.idfarmacia=f.idfarmacia)\n" +
                "where pf.idfarmacia=? limit " + inicio + ",6 ;";

        if (inicio == -1) {
            sql = "Select  p.idproducto,p.nombre, f.foto1,pf.stock,pf.preciounitario,pf.recetamedica from productoporfarmacia pf\n" +
                    "inner join producto p on (pf.idproducto=p.idproducto)\n" +
                    "left join foto f on (pf.idproducto=f.idproducto and pf.idfarmacia=f.idfarmacia)\n" +
                    "where pf.idfarmacia=?;";
        }


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idFarmacia);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    BDetProd detProd = new BDetProd();
                    detProd.setIdProducto(rs.getInt(1));
                    detProd.setNombre(rs.getString(2));
                    detProd.setFoto(rs.getString(3));
                    detProd.setStock(rs.getInt(4));
                    detProd.setPreciounitario(rs.getDouble(5));
                    detProd.setRecetaMedicaB(rs.getBoolean(6));


                    listarProductosF.add(detProd);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return listarProductosF;

    }

    public int obtenerNumProductos(String idFarmacia) {
        int numProductos = 0;

        String sql = "select count(*) from (SELECT * FROM productoporfarmacia where idfarmacia = ?) as `productos`;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, idFarmacia);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    numProductos = rs.getInt(1);
                    return numProductos;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo hallar en numero de productos");

        }
        return numProductos;
    }


    public BFarmaciaPorDistrito obtenerFarmacia(int idFarmacia) {
        //msql
        BFarmaciaPorDistrito farmacia = new BFarmaciaPorDistrito();
        String sql = "select u.nombre as 'Farmacias', dis.nombre as 'Distrito', f.direccion, f.idfarmacia from farmacia f\n" +
                "inner join usuario u on (f.idusuario = u.idusuario)\n" +
                "inner join distrito dis on (u.iddistrito = dis.iddistrito)\n" +
                "where f.idfarmacia = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idFarmacia);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    farmacia.setNombreFarmacia(rs.getString(1));
                    farmacia.setDistritoFarmacia(rs.getString(2));
                    farmacia.setDireccionFarmacia(rs.getString(3));
                    farmacia.setIdFarmacia(rs.getInt(4));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return farmacia;

    }


    public BDetProd detalleProductoF(int idFarmacia, int idProducto) {
        //msql
        BDetProd detProd = new BDetProd();

        String sql = "Select  p.idproducto,p.nombre, f.foto1,pf.stock, pf.descripcion,pf.preciounitario from productoporfarmacia pf\n" +
                "inner join producto p on (pf.idproducto=p.idproducto)\n" +
                "left join foto f on (pf.idproducto=f.idproducto and pf.idfarmacia=f.idfarmacia)\n" +
                "where pf.idfarmacia=? and p.idproducto=?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idFarmacia);
            pstmt.setInt(2, idProducto);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    detProd.setIdProducto(rs.getInt(1));
                    detProd.setNombre(rs.getString(2));
                    detProd.setFoto(rs.getString(3));
                    detProd.setStock(rs.getInt(4));
                    detProd.setDescripcion(rs.getString(5));
                    detProd.setPreciounitario(rs.getDouble(6));

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return detProd;
    }
}
