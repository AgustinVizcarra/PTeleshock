package pe.edu.pucp.pteleshock.Dao;



import pe.edu.pucp.pteleshock.Beans.BDistristos;
import pe.edu.pucp.pteleshock.Beans.BPedido;
import pe.edu.pucp.pteleshock.Beans.BPedidoEstado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoEstadoDao extends BaseDao{

    public ArrayList<BPedidoEstado> listarPedidosEstado(String idPG, int idPFd) {

        ArrayList<BPedidoEstado> listaPedidosE = new ArrayList<>();

        String sql = "select p.idpedido, p.codigodeventa, f.idfarmacia,u.nombre as 'Farmacia' , e.nombre as 'Estado', f.direccion ,dis.nombre as `distrito`, p.fechaentrega, p.fechastatus, foto.foto1 as 'Foto', pro.nombre as 'Producto', d.cantidad, pxf.preciounitario,round((d.cantidad*pxf.preciounitario),2) as 'Precio total c/producto',pxf.idproducto from usuario un\n" +
                "left join pedido p on (un.idusuario = p.idusuario)\n" +
                "left join estatuspedido e on (p.idestatuspedido = e.idestatuspedido)\n" +
                "left join detallepedido d on (p.idpedido = d.idpedido)\n" +
                "left join productoporfarmacia pxf on (d.idproducto = pxf.idproducto) and (d.idfarmacia = pxf.idfarmacia)\n" +
                "left join foto on (pxf.idproducto = foto.idproducto) and (foto.idfarmacia = pxf.idfarmacia)\n" +
                "left join producto pro on (pxf.idproducto = pro.idproducto)\n" +
                "left join farmacia f on (pxf.idfarmacia = f.idfarmacia)\n" +
                "left join usuario u on (f.idusuario = u.idusuario)\n" +
                "inner join distrito dis on (u.iddistrito = dis.iddistrito)\n" +
                "where (p.codigodeventa = ? and f.idfarmacia=?)\n" +
                "and e.idestatuspedido not in (1) order by p.codigodeventa;";

        try (Connection conn = this.getConnection() ;
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, idPG);
            pstmt.setInt(2, idPFd);


            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    BPedidoEstado pedidoE = new BPedidoEstado();

                    BPedido pedido = new BPedido();
                    pedido.setIdPedido(rs.getInt(1));
                    pedido.setBoletaVenta(rs.getString(2));
                    pedido.setIdFarmacia(rs.getInt(3));
                    pedido.setNombreFarmacia(rs.getString(4));
                    pedido.setFechaEntrega(rs.getString(8));
                    pedido.setEstadoPedido(rs.getString(5));

                    pedidoE.setPedido(pedido);

                    BDistristos distristos = new BDistristos();
                    distristos.setNombre(rs.getString(7));

                    pedidoE.setDistristos(distristos);

                    pedidoE.setFotoProducto(rs.getString(10));
                    pedidoE.setFechaStatus(rs.getString(9));
                    pedidoE.setDireccionFarm(rs.getString(6));
                    pedidoE.setPrecioProducto(rs.getDouble(14));
                    pedidoE.setNombreProducto(rs.getString(11));
                    pedidoE.setCantidad(rs.getInt(12));
                    pedidoE.setPrecioUnitario(rs.getDouble(13));
                    pedidoE.setIdProducto(rs.getInt(15));


                    listaPedidosE.add(pedidoE);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return listaPedidosE;
    }

    public boolean sePuedeCancelar (String idPG,int idPFd){

        Boolean seCancela = false;

        String sql = "SELECT p.idpedido, dp.idfarmacia, p.fechastatus , timestampdiff(hour,now(),fechaentrega) as `tiempoRestante` FROM  pedido p \n" +
                "left join detallepedido dp on (p.idpedido = dp.idpedido)\n" +
                "where (p.codigodeventa =? and dp.idfarmacia = ?) group by dp.idfarmacia;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, idPG);
            pstmt.setInt(2, idPFd);


            try (ResultSet rs = pstmt.executeQuery()) {
                int tiempoRestante;
                String status="";
                if (rs.next()) {
                    status=rs.getString(3);
                    tiempoRestante = rs.getInt(4);
                    if (tiempoRestante>=1){
                        if(status==null){
                            seCancela = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return seCancela;
    }

    /*public void listar_farmaciasXboleta(int boleta){
        ArrayList<BFarmacia> listaPedidosE = new ArrayList<>();

        String sql = "select p.idpedido, p.codigodeventa, f.idfarmacia,u.nombre as 'Farmacia' , e.nombre as 'Estado', f.direccion ,dis.nombre as `distrito`, p.fechaentrega, p.fechastatus, foto.foto1 as 'Foto', pro.nombre as 'Producto', d.cantidad, pxf.preciounitario,round((d.cantidad*pxf.preciounitario),2) as 'Precio total c/producto' from usuario un\n" +
                "left join pedido p on (un.idusuario = p.idusuario)\n" +
                "left join estatuspedido e on (p.idestatuspedido = e.idestatuspedido)\n" +
                "left join detallepedido d on (p.idpedido = d.idpedido)\n" +
                "left join productoporfarmacia pxf on (d.idproducto = pxf.idproducto) and (d.idfarmacia = pxf.idfarmacia)\n" +
                "left join foto on (pxf.idproducto = foto.idproducto) and (foto.idfarmacia = pxf.idfarmacia)\n" +
                "left join producto pro on (pxf.idproducto = pro.idproducto)\n" +
                "left join farmacia f on (pxf.idfarmacia = f.idfarmacia)\n" +
                "left join usuario u on (f.idusuario = u.idusuario)\n" +
                "inner join distrito dis on (u.iddistrito = dis.iddistrito)\n" +
                "where (p.codigodeventa = ? and f.idfarmacia=?)\n" +
                "and e.idestatuspedido not in (1) order by p.codigodeventa;";

        try (Connection conn = this.getConnection() ;
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, idPG);
            pstmt.setInt(2, idPFd);


            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    BPedidoEstado pedidoE = new BPedidoEstado();

                    BPedido pedido = new BPedido();

                    pedido.setBoletaVenta(rs.getString(2));
                    pedido.setNombreFarmacia(rs.getString(4));
                    pedido.setFechaEntrega(rs.getString(8));
                    pedido.setEstadoPedido(rs.getString(5));

                    pedidoE.setPedido(pedido);

                    BDistristos distristos = new BDistristos();
                    distristos.setNombre(rs.getString(7));

                    pedidoE.setDistristos(distristos);

                    pedidoE.setFotoProducto(rs.getString(10));
                    pedidoE.setFechaStatus(rs.getString(9));
                    pedidoE.setDireccionFarm(rs.getString(6));
                    pedidoE.setPrecioProducto(rs.getDouble(14));
                    pedidoE.setNombreProducto(rs.getString(11));
                    pedidoE.setCantidad(rs.getInt(12));
                    pedidoE.setPrecioUnitario(rs.getDouble(13));



                    listaPedidosE.add(pedidoE);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo realizar la busqueda");

        }
        return listaPedidosE;
    }*/

}
