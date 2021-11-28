package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BDistristos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ListaDistritosDao extends BaseDao {

    public ArrayList<BDistristos> listarDistritos() {
        //msql

        ArrayList<BDistristos> listaDistritos = new ArrayList<>();

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT d.iddistrito, d.nombre FROM distrito d inner join usuario u on (d.iddistrito = u.iddistrito) where idrol = 3 group by d.nombre order by d.nombre;");


            while (rs.next()) {
                BDistristos dis = new BDistristos();
                dis.setIdDistrito(rs.getInt(1));
                dis.setNombre(rs.getString(2));
                listaDistritos.add(dis);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDistritos;
    }

    public ArrayList<BDistristos> listarTotalDistritos() {
        //msql

        ArrayList<BDistristos> listaTotalDistritos = new ArrayList<>();

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT d.iddistrito, d.nombre FROM distrito d;");


            while (rs.next()) {
                BDistristos dis = new BDistristos();
                dis.setIdDistrito(rs.getInt(1));
                dis.setNombre(rs.getString(2));
                listaTotalDistritos.add(dis);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTotalDistritos;
    }
    
    
    
    
    
}
