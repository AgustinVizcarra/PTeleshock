package pe.edu.pucp.pteleshock.Dao;
import pe.edu.pucp.pteleshock.Beans.BCliente;
import java.sql.*;
import java.util.ArrayList;

public class RegistrarClienteDao extends BaseDao{

        public void añadirCliente(int idDis, String nombre, String apellido, String correo,String dni, String contrasenia) {

            String sql = "INSERT INTO usuario (idrol, iddistrito, nombre, apellido, correo, dni, contrasenia) VALUES (1,?,?,?,?,?, SHA2(?,256))";

            try (Connection conn = this.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idDis);
                pstmt.setString(2, nombre);
                pstmt.setString(3, apellido);
                pstmt.setString(4, correo);
                pstmt.setString(5, dni);
                pstmt.setString(6, contrasenia);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public ArrayList<BCliente> listaClientesR(){
        ArrayList<BCliente> listaClientes = new ArrayList<BCliente>();
        String sql="SELECT u.idusuario,u.correo,u.dni FROM usuario u;";

        try (Connection conn = this.getConnection();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)) {
            while (rs.next()) {
                BCliente bCliente = new BCliente();
                bCliente.setIdCliente(rs.getInt(1));
                bCliente.setCorreo(rs.getString(2));
                bCliente.setDni(rs.getString(3));
                listaClientes.add(bCliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listaClientes;
    }


    public boolean correoExistente (String correo){
        boolean siExiste = false;
        ArrayList<BCliente> listaClientes = listaClientesR();
        for(BCliente cliente : listaClientes){
            if(cliente.getCorreo().equals(correo)){
                siExiste = true;
                break;
            }
        }
        return siExiste;
    }

    public boolean dniExistente (String dni){
        boolean siExiste = false;
        ArrayList<BCliente> listaClientes = listaClientesR();
        for(BCliente cliente : listaClientes){
            if(cliente.getDni()!=null){
                if(cliente.getDni().equals(dni)){
                    siExiste = true;
                    break;
                }
            }
        }
        return siExiste;
    }


}
