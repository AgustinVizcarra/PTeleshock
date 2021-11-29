package pe.edu.pucp.pteleshock.Dao;




import pe.edu.pucp.pteleshock.Beans.BVentasPorFecha;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VentFechaDao extends BaseDao {

    public ArrayList<BVentasPorFecha> listventasFecha(int idFarmacia){
        ArrayList<BVentasPorFecha> listventasFecha = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select concat(extract(year from fechastatus),\"-\", extract(month from fechastatus),\"-\", extract(day from fechastatus)) as 'fecha',count(*) as 'número de ventas' from (select p.fechastatus, concat(extract(month from fechastatus),\"-\", extract(day from fechastatus)) as 'mesydia' from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "where  (p.idestatuspedido = 3) and dp.idfarmacia="+idFarmacia+" #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
                    "group by dp.idpedido\n" +
                    "order by p.fechastatus desc) subquery\n" +
                    "group by subquery.mesydia limit 0,8;");


            while (rs.next()) {

                BVentasPorFecha vefech = new BVentasPorFecha();

                vefech.setFecha(rs.getString(1));
                vefech.setNumventas(rs.getInt(2));


                listventasFecha.add(vefech);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listventasFecha;
    }


}
