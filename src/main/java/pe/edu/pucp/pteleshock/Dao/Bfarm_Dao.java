package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.Bbloqueadas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bfarm_Dao extends BaseDao {

    public ArrayList<Bbloqueadas> listar_bloqueadas(){
        ArrayList<Bbloqueadas> bbloqueadas = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs = statement.executeQuery("select a.nombre 'Administrador',farm.nombre 'Farmacia' ,historialadmin.razon 'Razon/Motivo' ,historialadmin.fechahora 'Fecha' from historialadmin \n" +
                    "inner join farmacia f on (historialadmin.idfarmacia = f.idfarmacia)\n" +
                    "inner join usuario a on (a.idusuario=historialadmin.idusuario)\n" +
                    "inner join accion on (accion.idaccion=historialadmin.idaccion)\n" +
                    "inner join usuario farm on (farm.idusuario=f.idusuario)\n" +
                    "where accion.idaccion=1;");
            while(rs.next()){
                Bbloqueadas bloqueada = new Bbloqueadas();
                bloqueada.setAdministrador_nombre(rs.getString(1));
                bloqueada.setFarmacia_nombre(rs.getString(2));
                bloqueada.setRazon_motivo(rs.getString(3));
                bloqueada.setFecha(rs.getString(4));
                bbloqueadas.add(bloqueada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bbloqueadas;
    }
}