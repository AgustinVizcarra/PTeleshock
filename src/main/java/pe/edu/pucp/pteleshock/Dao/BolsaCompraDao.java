package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.*;

import java.sql.*;
import java.util.ArrayList;

public class BolsaCompraDao extends BaseDao {

    public int generarPedidoCarrito() {

        int idPedido = -1;

        String sql = "INSERT INTO pedido (idusuario, idestatuspedido, preciototal) VALUES (10,1,0)";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            pstmt.executeUpdate();

            ResultSet rsKeys = pstmt.getGeneratedKeys();
            if (rsKeys.next()) {
                idPedido = rsKeys.getInt(1);
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

    public ArrayList<BPedidoEstado> listarPedidosCarrito(int idP) {

        ArrayList<BPedidoEstado> listaPedidosCarrito = new ArrayList<>();

        String sql = "SELECT pe.idpedido, dp.idproducto, dp.idfarmacia, p.nombre,pxf.recetamedica, dp.cantidad, pxf.preciounitario, f.foto1 FROM pedido pe \n" +
                "left join detallepedido dp on(pe.idpedido=dp.idpedido)\n" +
                "left join productoporfarmacia pxf on (dp.idproducto = pxf.idproducto and dp.idfarmacia = pxf.idfarmacia)\n" +
                "left join producto p on (p.idproducto = pxf.idproducto)\n" +
                "left join foto f on (dp.idproducto = f.idproducto and dp.idfarmacia = f.idfarmacia)\n" +
                "where pe.idestatuspedido = 1 and pe.idpedido = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idP);


            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    BPedidoEstado pedidoC = new BPedidoEstado();

                    BPedido pedido = new BPedido();

                    pedido.setIdPedido(rs.getInt(1));
                    pedido.setIdFarmacia(rs.getInt(3));


                    pedidoC.setPedido(pedido);


                    pedidoC.setFotoProducto(rs.getString(8));
                    pedidoC.setNombreProducto(rs.getString(4));
                    pedidoC.setCantidad(rs.getInt(6));
                    pedidoC.setPrecioUnitario(rs.getDouble(7));
                    pedidoC.setIdProducto(rs.getInt(2));
                    pedidoC.setRecetaMedica(rs.getBoolean(5));


                    listaPedidosCarrito.add(pedidoC);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return listaPedidosCarrito;
    }

    public void realizarPedido(int idPedido, String fecha, String hora, boolean receta, String fotoReceta) {


        String sql = "UPDATE pedido set idestatuspedido = 2, fechapedido = now() , fechaentrega = ? , codigodeventa = ? where idpedido = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, (fecha +" "+ hora));
            pstmt.setInt(2, (int) Math.floor(Math.random()*4000 + 1000));
            pstmt.setInt(3, idPedido);

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

        String sql = "UPDATE pedido set fechastatus=now() where idpedido=?;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1,idPedido);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
