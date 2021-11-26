package pe.edu.pucp.pteleshock.Dao;



import pe.edu.pucp.pteleshock.Beans.BDistrito;

import java.sql.*;
import java.util.ArrayList;

public class Distrfarm_Dao extends BaseDao {

    public ArrayList<BDistrito> listar_distritos(){
        ArrayList<BDistrito> distritos = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs = statement.executeQuery("select d.nombre,d.iddistrito from usuario u inner join distrito d on (d.iddistrito=u.iddistrito) where u.idrol=3 group by d.nombre");
            while(rs.next()){
                distritos.add(new BDistrito(rs.getString(1),rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return distritos;
    }

    public BDistrito obtenerDistritoPorId(String idDistrito){
        BDistrito distrito = null;

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM distrito where iddistrito = ?;");) {
            pstmt.setString(1, idDistrito);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    distrito = new BDistrito(rs.getString(2),rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en la busqueda del nombre de Distrito");
            e.printStackTrace();
        }
        return distrito;
    }
}