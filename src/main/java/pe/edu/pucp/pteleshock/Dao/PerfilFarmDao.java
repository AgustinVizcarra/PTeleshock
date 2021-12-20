package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.BPerfilFarmacia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PerfilFarmDao extends BaseDao{


    public ArrayList<BPerfilFarmacia> perfilFarmacia(int idFarmacia){
        ArrayList<BPerfilFarmacia> perfilFarmacia = new ArrayList<>();

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select u.nombre,u.correo,u.ruc,concat(f.direccion,\"-\",d.nombre) as 'direccion' \n" +
                    "from farmacia f\n" +
                    "inner join usuario u on (f.idusuario=u.idusuario)\n" +
                    "inner join distrito d on (u.iddistrito=d.iddistrito)\n" +
                    "where f.idfarmacia="+idFarmacia+";");


            while (rs.next()) {

                BPerfilFarmacia pf = new BPerfilFarmacia();
                pf.setNombre(rs.getString(1));
                pf.setCorreo(rs.getString(2));
                pf.setRuc(rs.getString(3));
                pf.setDireccion(rs.getString(4));
                perfilFarmacia.add(pf);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return perfilFarmacia;
    }


}
