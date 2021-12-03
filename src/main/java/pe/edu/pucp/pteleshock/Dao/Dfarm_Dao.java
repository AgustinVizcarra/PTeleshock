package pe.edu.pucp.pteleshock.Dao;



import pe.edu.pucp.pteleshock.Beans.Bdesbloqueadas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dfarm_Dao extends BaseDao{
    public ArrayList<Bdesbloqueadas> listar_desbloqueadas(){
        ArrayList<Bdesbloqueadas> bdesbloqueadas = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs = statement.executeQuery("select a.nombre 'Administrador',farm.nombre 'Farmacia' ,historialadmin.razon 'Razon/Motivo' ,historialadmin.fechahora 'Fecha' from historialadmin \n" +
                    "inner join farmacia f on (historialadmin.idfarmacia = f.idfarmacia)\n" +
                    "inner join usuario a on (a.idusuario=historialadmin.idusuario)\n" +
                    "inner join accion on (accion.idaccion=historialadmin.idaccion)\n" +
                    "inner join usuario farm on (farm.idusuario=f.idusuario)\n" +
                    "where accion.idaccion=2 order by historialadmin.fechahora desc");
            while(rs.next()){
                Bdesbloqueadas desbloqueada = new Bdesbloqueadas();
                desbloqueada.setAdministrador_nombre(rs.getString(1));
                desbloqueada.setFarmacia_nombre(rs.getString(2));
                desbloqueada.setRazon_motivo(rs.getString(3));
                desbloqueada.setFecha(rs.getString(4));
                bdesbloqueadas.add(desbloqueada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bdesbloqueadas;
    }
}