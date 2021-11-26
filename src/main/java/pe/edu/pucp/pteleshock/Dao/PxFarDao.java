package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BListaPFarmacia;

import java.sql.*;
import java.util.ArrayList;

public class PxFarDao extends BaseDao {

    public ArrayList<BListaPFarmacia> listarProductosF(String pag){
        ArrayList<BListaPFarmacia> listaProductosF = new ArrayList<>();
        int pagint =Integer.parseInt(pag);
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select  p.idproducto,p.nombre, f.foto1  from productoporfarmacia pf\n" +
                    " inner join producto p on (pf.idproducto=p.idproducto)\n" +
                    " left join foto f on (pf.idproducto=f.idproducto and pf.idfarmacia=f.idfarmacia)\n" +
                    "where pf.idfarmacia=2 limit "+(pagint-1)*12 +",12;");


            while (rs.next()) {
                BListaPFarmacia pxf = new BListaPFarmacia();
                pxf.setIdProducto(rs.getInt(1));
                pxf.setNombre(rs.getString(2));
                pxf.setFoto(rs.getString(3));
                listaProductosF.add(pxf);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductosF;
    }

    public ArrayList<BListaPFarmacia> buscarProductoPorNombre(String pag, String nombre) {
        ArrayList<BListaPFarmacia> listaProductosF = new ArrayList<>();
        int pagint =Integer.parseInt(pag);
        String sql = "Select  p.idproducto,p.nombre, f.foto1  from productoporfarmacia pf\n" +
                " inner join producto p on (pf.idproducto=p.idproducto)\n" +
                " left join foto f on (pf.idproducto=f.idproducto and pf.idfarmacia=f.idfarmacia)\n" +
                "where pf.idfarmacia=2 and lower(p.nombre) like ? limit "+(pagint-1)*12 +",12";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BListaPFarmacia pxf = new BListaPFarmacia();
                    pxf.setIdProducto(rs.getInt(1));
                    pxf.setNombre(rs.getString(2));
                    pxf.setFoto(rs.getString(3));
                    listaProductosF.add(pxf);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductosF;
    }

    public int cantidadProductosF(){
        int cant =0;
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(idproducto) FROM productoporfarmacia\n" +
                    "where idfarmacia=2;");


            while (rs.next()) {
                cant=rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cant;
    }



    public int cantidadProductosBuscados(String nombre){

        int cant =0;
        String sql ="Select  count(p.idproducto) from productoporfarmacia pf\n" +
                "inner join producto p on (pf.idproducto=p.idproducto)\n" +
                "left join foto f on (pf.idproducto=f.idproducto and pf.idfarmacia=f.idfarmacia)\n" +
                "where pf.idfarmacia=2 and lower(p.nombre) like ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    cant=rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cant;
    }

}
