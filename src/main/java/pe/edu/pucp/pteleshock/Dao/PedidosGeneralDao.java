package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BPedidoGeneral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PedidosGeneralDao extends BaseDao {

    public ArrayList<BPedidoGeneral> listarPedidosGeneral(int inicio, int idCliente) {
        //msql

        ArrayList<BPedidoGeneral> listaPedidosGeneral = new ArrayList<>();

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select un.idusuario,p.idpedido, p.codigodeventa, p.fechapedido, sum(round((d.cantidad*pxf.preciounitario),2)) as 'Precio total c/producto' from usuario un\n" +
                    "left join pedido p on (un.idusuario = p.idusuario)\n" +
                    "left join estatuspedido e on (p.idestatuspedido = e.idestatuspedido)\n" +
                    "left join detallepedido d on (p.idpedido = d.idpedido)\n" +
                    "left join productoporfarmacia pxf on ((d.idproducto = pxf.idproducto) and (d.idfarmacia = pxf.idfarmacia))\n" +
                    "left join producto pro on (pxf.idproducto = pro.idproducto)\n" +
                    "left join farmacia f on (pxf.idfarmacia = f.idfarmacia)\n" +
                    "left join usuario u on (f.idusuario = u.idusuario)\n" +
                    "where un.idusuario = " + idCliente + " and e.idestatuspedido not in (1)\n" +
                    "group by p.codigodeventa, p.fechapedido order by p.fechapedido desc limit " + inicio + ",5 ;");


            while (rs.next()) {
                BPedidoGeneral pedidoG = new BPedidoGeneral();
                pedidoG.setIdCliente(rs.getInt(1));
                pedidoG.setCodigoVenta(rs.getString(3));
                pedidoG.setFechaPedido(rs.getString(4));
                pedidoG.setMontoTotal(rs.getDouble(5));

                listaPedidosGeneral.add(pedidoG);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidosGeneral;

    }

    public int obtenerNumFilasPG(int idCliente) {

        int numFilasPG = 0;

        String sql = "SELECT count(*) FROM (SELECT * FROM pedido where (codigodeventa is not null and idusuario = "+ idCliente +") group by codigodeventa,fechapedido order by fechapedido) as `numPedidosG`;";

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                numFilasPG = rs.getInt(1);
                return numFilasPG;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numFilasPG;
    }


}
