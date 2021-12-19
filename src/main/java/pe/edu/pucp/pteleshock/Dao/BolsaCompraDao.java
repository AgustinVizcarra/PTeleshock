package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.*;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BolsaCompraDao extends BaseDao {

    public int generarPedidoCarrito(int iduser) {
        int idPedido = -1;

        String sql = "INSERT INTO pedido (idusuario, idestatuspedido, preciototal) VALUES (?,1,0)";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            pstmt.setInt(1, iduser);
            pstmt.executeUpdate();

            ResultSet rsKeys = pstmt.getGeneratedKeys();
            if (rsKeys.next()) {
                idPedido = rsKeys.getInt(1);
                System.out.println("entró a generar pedido");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return idPedido;

    }

    public void agregarProductoCarrito(int idPedido, int idProd, int cantidad, int idFarmacia) {

        if (idPedido != -1) {

            String sql2 = "INSERT INTO detallepedido (idpedido, idproducto, idfarmacia,cantidad) VALUES (?,?,?,?)";

            try (Connection connection = this.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql2)) {

                pstmt.setInt(1, idPedido);
                pstmt.setInt(2, idProd);
                pstmt.setInt(3, idFarmacia);
                pstmt.setInt(4, cantidad);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public BPedidoEstado pedidosCarrito(int idP,int idProd) {

        BPedidoEstado pedidoC = new BPedidoEstado();

        String sql = "SELECT pe.idpedido, dp.idproducto, dp.idfarmacia,u.nombre, p.nombre,pxf.recetamedica, dp.cantidad, pxf.preciounitario, f.foto1 FROM pedido pe\n" +
                "join detallepedido dp on(pe.idpedido=dp.idpedido)\n" +
                "left join productoporfarmacia pxf on (dp.idproducto = pxf.idproducto and dp.idfarmacia = pxf.idfarmacia)\n" +
                "left join producto p on (p.idproducto = pxf.idproducto)\n" +
                "left join foto f on (dp.idproducto = f.idproducto and dp.idfarmacia = f.idfarmacia)\n" +
                "left join farmacia far on (dp.idfarmacia=far.idfarmacia) \n" +
                "left join usuario u on (far.idusuario = u.idusuario)\n" +
                "where pe.idestatuspedido = 1 and pe.idpedido = ? and dp.idproducto = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idP);
            pstmt.setInt(2, idProd);



            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    BPedido pedido = new BPedido();

                    pedido.setIdPedido(rs.getInt(1));
                    pedido.setIdFarmacia(rs.getInt(3));
                    pedidoC.setPedido(pedido);
                    pedido.setNombreFarmacia(rs.getString(4));
                    pedidoC.setFotoProducto(rs.getString(9));
                    pedidoC.setNombreProducto(rs.getString(5));
                    pedidoC.setCantidad(rs.getInt(7));
                    pedidoC.setPrecioUnitario(rs.getDouble(8));
                    pedidoC.setIdProducto(rs.getInt(2));
                    pedidoC.setRecetaMedica(rs.getBoolean(6));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return pedidoC;
    }

    public void realizarPedido(int idPedido, String fecha, boolean receta, String fotoReceta, int codigoVenta,Double Subtotal) {


        String sql = "UPDATE pedido set idestatuspedido = 2, fechapedido = now() , preciototal=?, fechaentrega = ? , codigodeventa = ? where idpedido = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setDouble(1, Subtotal);
            pstmt.setString(2, fecha);
            pstmt.setInt(3, codigoVenta);
            pstmt.setInt(4, idPedido);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (receta){
            String sql1 = "UPDATE detallepedido set recetamedica = ? where idpedido = ?";

            try (Connection connection = this.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);) {

                pstmt.setString(1, fotoReceta);
                pstmt.setInt(2, idPedido);

                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void cancelarPedido(int idPedido){

        String sql = "UPDATE pedido set fechastatus=now(),idestatuspedido=4 where idpedido=?;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1,idPedido);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void eliminarCarrito(int idProducto,int idPed, int idFarm){

        String sql="delete from detallepedido where idproducto=? and idpedido=? and idfarmacia=?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idProducto);
            pstmt.setInt(2, idPed);
            pstmt.setInt(3,idFarm);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1="delete from pedido where idpedido=?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql1)) {
            pstmt.setInt(1,idPed);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public BPedidoEstado obtenerProd(int idProducto,int idPed, int idFarm){
        BPedidoEstado pedidoC = null;

        String sql = "SELECT pe.idpedido, dp.idproducto, dp.idfarmacia,u.nombre, p.nombre,pxf.recetamedica, dp.cantidad, pxf.preciounitario, f.foto1 FROM pedido pe\n" +
                "join detallepedido dp on(pe.idpedido=dp.idpedido)\n" +
                "left join productoporfarmacia pxf on (dp.idproducto = pxf.idproducto and dp.idfarmacia = pxf.idfarmacia)\n" +
                "left join producto p on (p.idproducto = pxf.idproducto)\n" +
                "left join foto f on (dp.idproducto = f.idproducto and dp.idfarmacia = f.idfarmacia)\n" +
                "left join farmacia far on (dp.idfarmacia=far.idfarmacia)\n" +
                "left join usuario u on (far.idusuario = u.idusuario)\n" +
                "where dp.idfarmacia = ? and pe.idpedido =? and dp.idproducto=?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFarm);
            pstmt.setInt(2, idPed);
            pstmt.setInt(3, idProducto);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pedidoC= new BPedidoEstado();
                    BPedido pedido = new BPedido();

                    pedido.setIdPedido(rs.getInt(1));
                    pedido.setIdFarmacia(rs.getInt(3));
                    pedidoC.setPedido(pedido);
                    pedido.setNombreFarmacia(rs.getString(4));
                    pedidoC.setFotoProducto(rs.getString(9));
                    pedidoC.setNombreProducto(rs.getString(5));
                    pedidoC.setCantidad(rs.getInt(7));
                    pedidoC.setPrecioUnitario(rs.getDouble(8));
                    pedidoC.setIdProducto(rs.getInt(2));
                    pedidoC.setRecetaMedica(rs.getBoolean(6));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidoC;
    }

    public void cantidad(int cantidad, int idFarm, int idPedido, int idProd){
        String sql = "UPDATE detallepedido set cantidad=? where idfarmacia = ? and idpedido =? and idproducto=?;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1,cantidad);
            pstmt.setInt(2,idFarm);
            pstmt.setInt(3,idPedido);
            pstmt.setInt(4,idProd);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void foto(String foto, int idFarm, int idPedido, int idProd){
        String sql = "UPDATE detallepedido set recetamedica=? where idfarmacia = ? and idpedido =? and idproducto=?;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,foto);
            pstmt.setInt(2,idFarm);
            pstmt.setInt(3,idPedido);
            pstmt.setInt(4,idProd);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarFotoreceta(int idFarm, int idPedido, int idProd, InputStream inputstream){

        String sql = "UPDATE detallepedido set recetamedica=? where idfarmacia = ? and idpedido =? and idproducto=?;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setBlob(1,inputstream);
            pstmt.setInt(2,idFarm);
            pstmt.setInt(3,idPedido);
            pstmt.setInt(4,idProd);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarStockPendiente(HashMap<Integer, ArrayList<BPedidoEstado>> map){


        for(Map.Entry<Integer, ArrayList<BPedidoEstado>> ee : map.entrySet()) {
            System.out.println("###");
            System.out.println(ee.getKey());
            int idfarm=ee.getKey();
            ArrayList<BPedidoEstado> values = ee.getValue();

            for (BPedidoEstado value : values) {
                System.out.println("#####");
                System.out.println(value.getCantidad());
                System.out.println(value.getIdProducto());
                System.out.println("#####");

                String sql = "update mydb.productoporfarmacia set stock= stock -"+value.getCantidad()+" where idproducto="+value.getIdProducto()+" and idfarmacia=?;";

                try (Connection conn = this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
                    pstmt.setInt(1, idfarm);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
    }


}
