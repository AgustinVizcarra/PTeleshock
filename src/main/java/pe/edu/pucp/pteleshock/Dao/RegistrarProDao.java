package pe.edu.pucp.pteleshock.Dao;

import java.io.InputStream;
import java.sql.*;

public class RegistrarProDao extends BaseDao{


    public void registrarProducto(String nombre, int stock, double preciounitario , String descripcion, InputStream inputstream, String recetamedica){
        int idproducto= 0;
        String sql1 = "INSERT INTO producto (nombre) VALUES (?)";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1, nombre);
            pstmt.executeUpdate();

            ResultSet idProd =pstmt.getGeneratedKeys();
            idProd.next();
            idproducto= idProd.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "INSERT INTO productoporfarmacia (idproducto,idfarmacia, stock,preciounitario,descripcion,recetamedica) VALUES (?,?,?,?,?,?)";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt2 = connection.prepareStatement(sql2);) {

            pstmt2.setInt(1, idproducto);
            pstmt2.setInt(2, 2); //ojo esta hardcodeado(asumiendo que es la farmacia2)
            pstmt2.setInt(3, stock);
            pstmt2.setDouble(4, preciounitario);
            pstmt2.setString(5, descripcion);
            pstmt2.setString(6, recetamedica);
            pstmt2.executeUpdate();
            System.out.println(idproducto);
            System.out.println(stock);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sql3 = "INSERT INTO foto (idfarmacia,idproducto,foto1) VALUES (?,?,?)";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt3 = connection.prepareStatement(sql3);) {

            pstmt3.setInt(1, 2);
            pstmt3.setInt(2, idproducto);
            pstmt3.setBlob(3, inputstream);
            pstmt3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public void actualizarProducto(int idproducto,int stock, double preciounitario , String descripcion,String recetamedica, InputStream inputstream){
        String sql2 = "update productoporfarmacia set stock= ?, preciounitario= ?, descripcion= ?,recetamedica= ?  where idproducto=? and idfarmacia=2";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt2 = connection.prepareStatement(sql2);) {

            pstmt2.setInt(1, stock);
            pstmt2.setDouble(2, preciounitario); //ojo esta hardcodeado(asumiendo que es la farmacia2)
            pstmt2.setString(3, descripcion);
            pstmt2.setString(4, recetamedica);
            pstmt2.setInt(5, idproducto);

            pstmt2.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql3 = "update foto set foto1=?  where idproducto=? and idfarmacia=2";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt3 = connection.prepareStatement(sql3);) {

            pstmt3.setBlob(1,inputstream);
            pstmt3.setInt(2, idproducto);
            pstmt3.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
