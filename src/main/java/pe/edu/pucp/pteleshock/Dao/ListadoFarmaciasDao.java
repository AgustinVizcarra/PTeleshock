package pe.edu.pucp.pteleshock.Dao;



import pe.edu.pucp.pteleshock.Beans.BDistrito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ListadoFarmaciasDao extends BaseDao {
    public ArrayList<BDistrito> listar_distritos(){
        ArrayList<BDistrito> bDistritos = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs = statement.executeQuery("select d.nombre,d.iddistrito from distrito d");
            while(rs.next()){
                bDistritos.add(new BDistrito(rs.getString(1),rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bDistritos;
    }
}
