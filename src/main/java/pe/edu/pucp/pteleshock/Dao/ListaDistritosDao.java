package pe.edu.pucp.pteleshock.Dao;


import pe.edu.pucp.pteleshock.Beans.BDistristos;

import java.sql.*;
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

    public BDistristos obtenerDistritoPorId(int idDistrito) {

        BDistristos distrito = null;


        String sql = "select * from distrito where iddistrito = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setInt(1, idDistrito);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    distrito = new BDistristos();
                    distrito.setIdDistrito(idDistrito);
                    distrito.setNombre(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return distrito;
    }



}
