package pe.edu.pucp.pteleshock.Dao;



import pe.edu.pucp.pteleshock.Beans.BAnadidas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Afarm_Dao extends BaseDao {
    public ArrayList<BAnadidas> listar_anadidas(){
        ArrayList<BAnadidas> lista_farmacia_anadidas= new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs= statement.executeQuery("select a.nombre 'Administrador',farm.nombre 'Farmacia' ,farm.ruc as 'RUC' ,historialadmin.fechahora 'Fecha' from historialadmin \n" +
                    "inner join farmacia f on (historialadmin.idfarmacia = f.idfarmacia)\n" +
                    "inner join usuario a on (a.idusuario=historialadmin.idusuario)\n" +
                    "inner join accion on (accion.idaccion=historialadmin.idaccion)\n" +
                    "inner join usuario farm on (farm.idusuario=f.idusuario)\n" +
                    "where accion.idaccion=3 order by historialadmin.fechahora desc");
            while(rs.next()){
                BAnadidas farmacia = new BAnadidas();
                farmacia.setAdministrador_nombre(rs.getString(1));
                farmacia.setFarmacia_nombre(rs.getString(2));
                farmacia.setRuc(rs.getString(3));
                farmacia.setFecha(rs.getString(4));
                lista_farmacia_anadidas.add(farmacia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Hola hay un error");
        }
        return lista_farmacia_anadidas;
    }
}
