package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BDetProdFarm;
import pe.edu.pucp.pteleshock.Beans.BListaPFarmacia;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

//detalle por producto
public class DetProdDao extends BaseDao {

    public ArrayList<BDetProdFarm> listadetallesP(int idFarmacia,String prod){
        ArrayList<BDetProdFarm> listadetallesP = new ArrayList<>();

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT p.nombre,pf.recetamedica,pf.stock,pf.preciounitario,pf.descripcion,f.foto1\n" +
                    "FROM productoporfarmacia pf \n" +
                    "left join producto p on (p.idproducto=pf.idproducto)\n" +
                    "left join foto f on (f.idfarmacia=pf.idfarmacia and f.idproducto=pf.idproducto)\n" +
                    "where   pf.idfarmacia="+idFarmacia+" and p.idproducto='"+prod+"';");//Ojo es dinamico el idproducto


            while (rs.next()) {
                BDetProdFarm dp = new BDetProdFarm();
                dp.setNombre(rs.getString(1));
                dp.setRecetamedica(rs.getString(2));
                dp.setStock(rs.getInt(3));
                dp.setPreciounitario(rs.getDouble(4));
                dp.setDescripcion(rs.getString(5));
                dp.setFoto(rs.getBinaryStream(6));

                listadetallesP.add(dp);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listadetallesP;
    }




    public BListaPFarmacia existeProParaEliminar(int idFarmacia,String prod){
        BListaPFarmacia bListaPFarmacia=null;

        String sql1 = "select * from (SELECT pf.idproducto idproducto FROM productoporfarmacia pf\n" +
                "inner join detallepedido dp on (dp.idproducto=pf.idproducto)\n" +
                "inner join pedido p on (p.idpedido=dp.idpedido)\n" +
                "inner join estatuspedido sp on (sp.idestatuspedido=p.idestatuspedido)\n" +
                "where sp.idestatuspedido =2 and pf.idfarmacia="+idFarmacia+") tb\n" +
                "where tb.idproducto =(?)\n" +
                "group by tb.idproducto;";


        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql1);) {

            pstmt.setString(1, prod);
            try( ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    bListaPFarmacia = new BListaPFarmacia(); //
                    bListaPFarmacia.setIdProducto(rs.getInt(1));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  bListaPFarmacia;

    }


    public void borrarProducto(int idFarmacia,int idProd) throws SQLException {

        String sql1 = "delete from foto where idproducto=? and idfarmacia="+idFarmacia+";";


        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql1);) {
            pstmt.setInt(1, idProd);
            pstmt.executeUpdate();
        }


        String sql = "delete from productoporfarmacia \n" +
                "where idproducto = ? and idfarmacia="+idFarmacia+";\n" ;


        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setInt(1, idProd);
            pstmt.executeUpdate();
        }
    }


    public void productoeliminadostock0(int idFarmacia,int idproducto,int stock) throws SQLException{
        String sql2 = "update productoporfarmacia set stock= ? where idproducto=? and idfarmacia="+idFarmacia+";";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt2 = connection.prepareStatement(sql2);) {

            pstmt2.setInt(1, stock);
            pstmt2.setInt(2, idproducto);

            pstmt2.executeUpdate();


        }
    }

    public void productoeliminadologico(int idFarmacia,int idproducto) throws SQLException{
        String sql2 = "update productoporfarmacia set estadoproducto= 'eliminado' where idproducto=? and idfarmacia="+idFarmacia+";";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt2 = connection.prepareStatement(sql2);) {

            pstmt2.setInt(1, idproducto);

            pstmt2.executeUpdate();


        }
    }




    public void listarImg (int idFarmacia,String prod, HttpServletResponse response){
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima"; //ojo con el nombre
        InputStream inputStream=null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        response.setContentType("image/*");
        try {
            outputStream=response.getOutputStream();
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT p.nombre,pf.recetamedica,pf.stock,pf.preciounitario,pf.descripcion,f.foto1\n" +
                    "FROM productoporfarmacia pf \n" +
                    "left join producto p on (p.idproducto=pf.idproducto)\n" +
                    "left join foto f on (f.idfarmacia=pf.idfarmacia and f.idproducto=pf.idproducto)\n" +
                    "where   pf.idfarmacia="+idFarmacia+" and p.idproducto='"+prod+"';");//Ojo es dinamico el idproducto


            if(rs.next()){
                inputStream=rs.getBinaryStream("foto1");
            }
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOutputStream=new BufferedOutputStream(outputStream);
            int i =0;
            while ((i=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(i);
            }
            bufferedOutputStream.flush();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public void listarImgReceta (int idFarmacia,String prod,int idPedido, HttpServletResponse response){
        InputStream inputStream=null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        response.setContentType("image/*");
        try {
            outputStream=response.getOutputStream();
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.detallepedido\n" +
                    "where idpedido="+idPedido+" and idfarmacia="+idFarmacia+" and idproducto="+prod+";");//Ojo es dinamico el idproducto


            if(rs.next()){
                inputStream=rs.getBinaryStream("recetamedica");
            }
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOutputStream=new BufferedOutputStream(outputStream);
            int i =0;
            while ((i=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(i);
            }
            bufferedOutputStream.flush();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }




}
