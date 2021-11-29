package pe.edu.pucp.pteleshock.Dao;



import pe.edu.pucp.pteleshock.Beans.BEstado;
import pe.edu.pucp.pteleshock.Beans.BPedidoD;
import pe.edu.pucp.pteleshock.Beans.BPedidoFarm;
import pe.edu.pucp.pteleshock.Beans.BPedidoG;

import java.sql.*;
import java.util.ArrayList;

public class GPedidoDao extends BaseDao {
    public ArrayList<BPedidoG> listaPedidos(){
        ArrayList<BPedidoG> listaPedidos = new ArrayList<>();


        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select p.idpedido,p.fechapedido, u.nombre,u.apellido,u.dni,p.codigodeventa,p.preciototal from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia=2 #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
                    "group by dp.idpedido\n" +
                    "order by p.fechapedido desc;");


            while (rs.next()) {
                BPedidoG bpg = new BPedidoG();
                bpg.setIdPedido(rs.getInt(1));
                bpg.setFecha(rs.getString(2));
                bpg.setNombre(rs.getString(3));
                bpg.setApellido(rs.getString(4));
                bpg.setDni(rs.getString(5));
                bpg.setCodigoV(rs.getString(6));
                bpg.setPrecioTotal(rs.getDouble(7));
                listaPedidos.add(bpg);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidos;
    }

    public ArrayList<BPedidoG> listaPedidosPag(String pag){
        ArrayList<BPedidoG> listaPedidos = new ArrayList<>();
        int pagint =Integer.parseInt(pag);


        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select p.idpedido,p.fechapedido, u.nombre,u.apellido,u.dni,p.codigodeventa,p.preciototal from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia=2 #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
                    "group by dp.idpedido\n" +
                    "order by p.fechapedido desc limit "+(pagint-1)*3 +",3;");


            while (rs.next()) {
                BPedidoG bpg = new BPedidoG();
                bpg.setIdPedido(rs.getInt(1));
                bpg.setFecha(rs.getString(2));
                bpg.setNombre(rs.getString(3));
                bpg.setApellido(rs.getString(4));
                bpg.setDni(rs.getString(5));
                bpg.setCodigoV(rs.getString(6));
                bpg.setPrecioTotal(rs.getDouble(7));
                listaPedidos.add(bpg);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidos;
    }



    public int cantidadPedidos(){
        int cant =0;
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from (select count(*) from detallepedido dp\n" +
                    "                    inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "                    inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "                    where not (p.idestatuspedido = 1) and dp.idfarmacia=2 \n" +
                    "                    group by dp.idpedido) tabla;");


            while (rs.next()) {
                cant=rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cant;
    }

    public int cantidadPedidosBuscados(String texto){
        int cant =0;

        String sql="select count(*) from (select count(*) from detallepedido dp\n" +
                "                                   inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                "                                   inner join usuario u on (p.idusuario=u.idusuario)\n" +
                "                                    where not (p.idestatuspedido = 1) and dp.idfarmacia=2 and (lower(u.nombre) like ? or lower(u.apellido) like ? or u.dni like ?)  #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
                "                                  group by dp.idpedido\n" +
                "                                  order by p.fechapedido desc) tabla;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, "%" + texto + "%");
            pstmt.setString(2, "%" + texto + "%");
            pstmt.setString(3, "%" + texto + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    cant=rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cant;
    }


    public ArrayList<BPedidoD> listaPedidosD(int id){
        ArrayList<BPedidoD> listaPedidosD = new ArrayList<>();

        try {
            Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select p.idpedido,p.fechapedido,p.codigodeventa,ep.nombre as 'estado' , u.nombre,u.apellido,u.dni,\n" +
                    "u.correo,d.nombre as 'distrito'\n" +
                    "from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "inner join productoporfarmacia pf on (dp.idproducto=pf.idproducto and dp.idfarmacia=pf.idfarmacia)\n" +
                    "inner join producto pr on (pf.idproducto=pr.idproducto)\n" +
                    "inner join distrito d on(u.iddistrito=d.iddistrito)\n" +
                    "inner join estatuspedido ep on (ep.idestatuspedido=p.idestatuspedido)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia=2 and p.idpedido=?;");

            pstmt.setInt(1,id);
            try(ResultSet rs=pstmt.executeQuery()){
                if (rs.next()){
                    BPedidoD bpd = new BPedidoD();
                    bpd.setIdpedido(rs.getInt(1));
                    bpd.setFecha(rs.getString(2));
                    bpd.setCodigoV(rs.getString(3));
                    bpd.setEstado(rs.getString(4));
                    bpd.setNombre(rs.getString(5));
                    bpd.setApellido(rs.getString(6));
                    bpd.setDni(rs.getString(7));
                    bpd.setCorreo(rs.getString(8));
                    bpd.setDistrito(rs.getString(9));
                    listaPedidosD.add(bpd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidosD;
    }

    public ArrayList<BPedidoD> listaProductos(int id){
        ArrayList<BPedidoD> listaPedidosD = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select pr.nombre as 'Producto',\n" +
                    "pf.descripcion, dp.cantidad as 'Unidades', pf.preciounitario,pf.recetamedica\n" +
                    "from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "inner join productoporfarmacia pf on (dp.idproducto=pf.idproducto and dp.idfarmacia=pf.idfarmacia)\n" +
                    "inner join producto pr on (pf.idproducto=pr.idproducto)\n" +
                    "inner join distrito d on(u.iddistrito=d.iddistrito)\n" +
                    "inner join estatuspedido ep on (ep.idestatuspedido=p.idestatuspedido)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia=2 and p.idpedido=?;");

            pstmt.setInt(1,id);
            try(ResultSet rs=pstmt.executeQuery()){
                while (rs.next()) {
                    BPedidoD bpd = new BPedidoD();
                    bpd.setProducto(rs.getString(1));
                    bpd.setDescripcion(rs.getString(2));
                    bpd.setUnidades(rs.getInt(3));
                    bpd.setPrecioUnitario(rs.getDouble(4));
                    bpd.setRecetamedica(rs.getBoolean(5));
                    listaPedidosD.add(bpd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidosD;
    }


    public ArrayList<BEstado> listaEstados(){
        ArrayList<BEstado> listaEstados = new ArrayList<>();


        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM estatuspedido where not idestatuspedido=1;");


            while (rs.next()) {
                BEstado bEstado = new BEstado();
                bEstado.setIdEstado(rs.getInt(1));
                bEstado.setEstado(rs.getString(2));
                listaEstados.add(bEstado);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEstados;
    }

    public void actualizarestado(int idEstado,int idPedido) {

        System.out.println("actu:"+idEstado+"ped:"+idPedido);
        String sql="UPDATE pedido SET `idestatuspedido` = ? WHERE (`idpedido` = ?);";
        try (Connection conn = this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,idEstado);
            pstmt.setInt(2,idPedido);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BPedidoFarm obtenerPedidoId(int idPedido){
        BPedidoFarm bPedidoFarm =null;
        String sql = "SELECT idpedido,idestatuspedido FROM pedido where idpedido=?;";
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, idPedido);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    bPedidoFarm.setIdPedido(rs.getInt(1));
                    bPedidoFarm.setIdEstado(rs.getInt(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bPedidoFarm;
    }

    public ArrayList<BPedidoG> listaPedidosporNombre(String pag,String nombre){
        ArrayList<BPedidoG> listaPedidos = new ArrayList<>();

        int pagint =Integer.parseInt(pag);
        String sql = "select p.idpedido,p.fechapedido, u.nombre,u.apellido,u.dni,p.codigodeventa,p.preciototal from detallepedido dp\n" +
                "                    inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                "                    inner join usuario u on (p.idusuario=u.idusuario)\n" +
                "                    where not (p.idestatuspedido = 1) and dp.idfarmacia=2 and (lower(u.nombre) like ? or lower(u.apellido) like ? or u.dni like ?)  #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
                "                    group by dp.idpedido\n" +
                "                    order by p.fechapedido desc limit "+(pagint-1)*3 +",3;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, "%" + nombre + "%");
            pstmt.setString(2, "%" + nombre + "%");
            pstmt.setString(3, "%" + nombre + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BPedidoG bpg = new BPedidoG();
                    bpg.setIdPedido(rs.getInt(1));
                    bpg.setFecha(rs.getString(2));
                    bpg.setNombre(rs.getString(3));
                    bpg.setApellido(rs.getString(4));
                    bpg.setDni(rs.getString(5));
                    bpg.setCodigoV(rs.getString(6));
                    bpg.setPrecioTotal(rs.getDouble(7));
                    listaPedidos.add(bpg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidos;

    }

}