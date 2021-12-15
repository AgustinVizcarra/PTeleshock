package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDao extends BaseDao {

    public ArrayList<BPedido> listarPedidos(String idPG, int inicio) {

        ArrayList<BPedido> listaPedidos = new ArrayList<>();

        String sql = "select p.idpedido, p.codigodeventa, f.idfarmacia,u.nombre as 'Farmacia' , e.nombre as 'Estado',p.fechapedido, p.fechaentrega, sum(round((d.cantidad*pxf.preciounitario),2)) as 'Precio total c/producto' from usuario un\n" +
                "left join pedido p on (un.idusuario = p.idusuario)\n" +
                "left join estatuspedido e on (p.idestatuspedido = e.idestatuspedido)\n" +
                "left join detallepedido d on (p.idpedido = d.idpedido)\n" +
                "left join productoporfarmacia pxf on ((d.idproducto = pxf.idproducto) and (d.idfarmacia = pxf.idfarmacia))\n" +
                "left join producto pro on (pxf.idproducto = pro.idproducto)\n" +
                "left join farmacia f on (pxf.idfarmacia = f.idfarmacia)\n" +
                "left join usuario u on (f.idusuario = u.idusuario)\n" +
                "where ((p.codigodeventa = ?) and e.idestatuspedido not in (1) )\n" +
                "group by p.idpedido ,fechaentrega order by fechaentrega limit " + inicio + ",3 ;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, idPG);


            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    BPedido pedido = new BPedido();
                    pedido.setBoletaVenta(rs.getString(2));
                    pedido.setIdFarmacia(rs.getInt(3));
                    pedido.setNombreFarmacia(rs.getString(4));
                    pedido.setFechaPedido(rs.getString(6));
                    pedido.setFechaEntrega(rs.getString(7));
                    pedido.setEstadoPedido(rs.getString(5));
                    pedido.setPrecioTotal(rs.getDouble(8));

                    listaPedidos.add(pedido);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda de pedidos");

        }
        return listaPedidos;
    }

    public int obtenerNumPedidos(String idPG, int idCliente) {

        int numPedidos = 0;

        String sql = "SELECT count(*) FROM (SELECT idpedido, codigodeventa FROM pedido where (codigodeventa is not null and idusuario = " + idCliente + " and codigodeventa =?)) as `pedidos`;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, idPG);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    numPedidos = rs.getInt(1);
                    return numPedidos;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo hallar en numero de pedidos");

        }
        return numPedidos;
    }


}
