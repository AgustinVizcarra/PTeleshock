package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.BCliente;
import pe.edu.pucp.pteleshock.Beans.BDistristos;
import pe.edu.pucp.pteleshock.Beans.BPerfilFarmacia;

import java.io.InputStream;
import java.sql.*;

public class RegistrarProDao extends BaseDao{


    public void registrarProducto(int idFarmacia , String nombre, int stock, double preciounitario , String descripcion, InputStream inputstream, String recetamedica){

        String nombre1 = nombre.toUpperCase().charAt(0) + nombre.substring(1, nombre.length()).toLowerCase();


        int idproducto= 0;
        String sql1 = "INSERT INTO producto (nombre) VALUES (?)";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1, nombre1);
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
            pstmt2.setInt(2, idFarmacia); //ojo esta hardcodeado(asumiendo que es la farmacia2)
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

            pstmt3.setInt(1, idFarmacia);
            pstmt3.setInt(2, idproducto);
            pstmt3.setBlob(3, inputstream);
            pstmt3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public int existeProducto(int idFarmacia , String nombre){
        String nombre1 = nombre.toUpperCase().charAt(0) + nombre.substring(1, nombre.length()).toLowerCase();

        int idproducto = 0;
        String sql = "SELECT pf.idproducto FROM productoporfarmacia pf\n" +
                "inner join producto p on p.idproducto=pf.idproducto\n" +
                "where p.nombre like"+nombre1+" and pf.idfarmacia="+idFarmacia+";";

        try (Connection conn1 = this.getConnection();
             Statement stmt = conn1.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if(rs.next()){
               idproducto=rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return  idproducto;

    }






    public void actualizarProducto(int idFarmacia,int idproducto,int stock, double preciounitario , String descripcion,String recetamedica, InputStream inputstream){
        String sql2 = "update productoporfarmacia set stock= ?, preciounitario= ?, descripcion= ?,recetamedica= ?  where idproducto=? and idfarmacia="+idFarmacia+";";

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

        String sql3 = "update foto set foto1=?  where idproducto=? and idfarmacia="+idFarmacia+";";

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
