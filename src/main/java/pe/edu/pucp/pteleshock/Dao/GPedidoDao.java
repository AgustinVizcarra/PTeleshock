package pe.edu.pucp.pteleshock.Dao;



import pe.edu.pucp.pteleshock.Beans.BEstado;
import pe.edu.pucp.pteleshock.Beans.BPedidoD;
import pe.edu.pucp.pteleshock.Beans.BPedidoFarm;
import pe.edu.pucp.pteleshock.Beans.BPedidoG;

import java.sql.*;
import java.util.ArrayList;

public class GPedidoDao extends BaseDao {
    public ArrayList<BPedidoG> listaPedidos(int idFarmacia){
        ArrayList<BPedidoG> listaPedidos = new ArrayList<>();


        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select p.idpedido,p.fechapedido, u.nombre,u.apellido,u.dni,p.codigodeventa,p.preciototal from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+" #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
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

    public ArrayList<BPedidoG> listaPedidosPag(int idFarmacia,String pag){
        ArrayList<BPedidoG> listaPedidos = new ArrayList<>();
        int pagint =Integer.parseInt(pag);


        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select p.idpedido,p.fechapedido,p.fechaentrega, u.nombre,u.apellido,u.dni,p.codigodeventa,p.preciototal from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+" #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
                    "group by dp.idpedido\n" +
                    "order by p.fechapedido desc limit "+(pagint-1)*6 +",6;");


            while (rs.next()) {
                BPedidoG bpg = new BPedidoG();
                bpg.setIdPedido(rs.getInt(1));
                bpg.setFecha(rs.getString(2));
                bpg.setFechaEntrega(rs.getString(3));
                bpg.setNombre(rs.getString(4));
                bpg.setApellido(rs.getString(5));
                bpg.setDni(rs.getString(6));
                bpg.setCodigoV(rs.getString(7));
                bpg.setPrecioTotal(rs.getDouble(8));
                listaPedidos.add(bpg);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidos;
    }



    public int cantidadPedidos(int idFarmacia){
        int cant =0;
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from (select count(*) from detallepedido dp\n" +
                    "                    inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "                    inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "                    where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+"\n" +
                    "                    group by dp.idpedido) tabla;");


            while (rs.next()) {
                cant=rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cant;
    }

    public int cantidadPedidosBuscados(int idFarmacia,String texto){
        int cant =0;

        String sql="select count(*) from (select count(*) from detallepedido dp\n" +
                "                                   inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                "                                   inner join usuario u on (p.idusuario=u.idusuario)\n" +
                "                                    where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+" and (lower(u.nombre) like ? or lower(u.apellido) like ? or u.dni like ? or p.fechapedido like ?)  #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
                "                                  group by dp.idpedido\n" +
                "                                  order by p.fechapedido desc) tabla;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, "%" + texto + "%");
            pstmt.setString(2, "%" + texto + "%");
            pstmt.setString(3, "%" + texto + "%");
            pstmt.setString(4, "%" + texto + "%");
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


    public ArrayList<BPedidoD> listaPedidosD(int idFarmacia,int id){
        ArrayList<BPedidoD> listaPedidosD = new ArrayList<>();

        try {
            Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select p.idpedido,p.fechapedido,p.fechaentrega,p.fechastatus,p.codigodeventa,ep.nombre as 'estado' , u.nombre,u.apellido,u.dni,\n" +
                    "u.correo,d.nombre as 'distrito'\n" +
                    "from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "inner join productoporfarmacia pf on (dp.idproducto=pf.idproducto and dp.idfarmacia=pf.idfarmacia)\n" +
                    "inner join producto pr on (pf.idproducto=pr.idproducto)\n" +
                    "inner join distrito d on(u.iddistrito=d.iddistrito)\n" +
                    "inner join estatuspedido ep on (ep.idestatuspedido=p.idestatuspedido)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+"  and p.idpedido=?;");

            pstmt.setInt(1,id);
            try(ResultSet rs=pstmt.executeQuery()){
                if (rs.next()){
                    BPedidoD bpd = new BPedidoD();
                    bpd.setIdpedido(rs.getInt(1));
                    bpd.setFecha(rs.getString(2));
                    bpd.setFechaEntrega(rs.getString(3));
                    if(rs.getString(4)==null){
                        bpd.setFechaEstatus("no");
                    }else{
                        bpd.setFechaEstatus(rs.getString(4));
                    }
                    bpd.setCodigoV(rs.getString(5));
                    bpd.setEstado(rs.getString(6));
                    bpd.setNombre(rs.getString(7));
                    bpd.setApellido(rs.getString(8));
                    bpd.setDni(rs.getString(9));
                    bpd.setCorreo(rs.getString(10));
                    bpd.setDistrito(rs.getString(11));
                    listaPedidosD.add(bpd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidosD;
    }

    public ArrayList<BPedidoD> listaProductos(int idFarmacia,int id){
        ArrayList<BPedidoD> listaPedidosD = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select pr.nombre as 'Producto',\n" +
                    "pf.descripcion, dp.cantidad as 'Unidades', pf.preciounitario,pf.recetamedica,pf.idproducto\n" +
                    "from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "inner join productoporfarmacia pf on (dp.idproducto=pf.idproducto and dp.idfarmacia=pf.idfarmacia)\n" +
                    "inner join producto pr on (pf.idproducto=pr.idproducto)\n" +
                    "inner join distrito d on(u.iddistrito=d.iddistrito)\n" +
                    "inner join estatuspedido ep on (ep.idestatuspedido=p.idestatuspedido)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+"  and p.idpedido=?;");

            pstmt.setInt(1,id);
            try(ResultSet rs=pstmt.executeQuery()){
                while (rs.next()) {
                    BPedidoD bpd = new BPedidoD();
                    bpd.setProducto(rs.getString(1));
                    bpd.setDescripcion(rs.getString(2));
                    bpd.setUnidades(rs.getInt(3));
                    bpd.setPrecioUnitario(rs.getDouble(4));
                    bpd.setRecetamedica(rs.getBoolean(5));
                    bpd.setIdproducto(rs.getInt(6));
                    listaPedidosD.add(bpd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidosD;
    }

    public ArrayList<BPedidoD> listaProductospag(int idFarmacia,int id,String pag){
        ArrayList<BPedidoD> listaPedidosD = new ArrayList<>();
        int pagint =Integer.parseInt(pag);
        try {
            Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("select pr.nombre as 'Producto',\n" +
                    "pf.descripcion, dp.cantidad as 'Unidades', pf.preciounitario,pf.recetamedica,pf.idproducto\n" +
                    "from detallepedido dp\n" +
                    "inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "inner join productoporfarmacia pf on (dp.idproducto=pf.idproducto and dp.idfarmacia=pf.idfarmacia)\n" +
                    "inner join producto pr on (pf.idproducto=pr.idproducto)\n" +
                    "inner join distrito d on(u.iddistrito=d.iddistrito)\n" +
                    "inner join estatuspedido ep on (ep.idestatuspedido=p.idestatuspedido)\n" +
                    "where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+"  and p.idpedido=? limit "+(pagint-1)*6 +",6;");

            pstmt.setInt(1,id);
            try(ResultSet rs=pstmt.executeQuery()){
                while (rs.next()) {
                    BPedidoD bpd = new BPedidoD();
                    bpd.setProducto(rs.getString(1));
                    bpd.setDescripcion(rs.getString(2));
                    bpd.setUnidades(rs.getInt(3));
                    bpd.setPrecioUnitario(rs.getDouble(4));
                    bpd.setRecetamedica(rs.getBoolean(5));
                    bpd.setIdproducto(rs.getInt(6));
                    listaPedidosD.add(bpd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidosD;
    }

    public int cantidadProductos1(int idFarmacia,int id){
        int cant =0;
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*)\n" +
                    "                    from detallepedido dp\n" +
                    "                    inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                    "                    inner join usuario u on (p.idusuario=u.idusuario)\n" +
                    "                    inner join productoporfarmacia pf on (dp.idproducto=pf.idproducto and dp.idfarmacia=pf.idfarmacia)\n" +
                    "                    inner join producto pr on (pf.idproducto=pr.idproducto)\n" +
                    "                    inner join distrito d on(u.iddistrito=d.iddistrito)\n" +
                    "                    inner join estatuspedido ep on (ep.idestatuspedido=p.idestatuspedido)\n" +
                    "                    where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+" and p.idpedido="+id+";");


            while (rs.next()) {
                cant=rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cant;
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
        String sql="UPDATE pedido SET `idestatuspedido` = ?,`fechastatus`= now() WHERE (`idpedido` = ?);";
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

    public ArrayList<BPedidoG> listaPedidosporNombre(int idFarmacia,String pag,String nombre){
        ArrayList<BPedidoG> listaPedidos = new ArrayList<>();

        int pagint =Integer.parseInt(pag);
        String sql = "select p.idpedido,p.fechapedido, u.nombre,u.apellido,u.dni,p.codigodeventa,p.preciototal,p.fechaentrega from detallepedido dp\n" +
                "                    inner join pedido p on (dp.idpedido=p.idpedido)\n" +
                "                    inner join usuario u on (p.idusuario=u.idusuario)\n" +
                "                    where not (p.idestatuspedido = 1) and dp.idfarmacia="+idFarmacia+"  and (lower(u.nombre) like ? or lower(u.apellido) like ? or u.dni like ? or date(p.fechapedido) like ?)  #idfarmacia es un parámetro que varía de acuerdo a la farmacia\n" +
                "                    group by dp.idpedido\n" +
                "                    order by p.fechapedido desc limit "+(pagint-1)*6 +",6;";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, "%" + nombre + "%");
            pstmt.setString(2, "%" + nombre + "%");
            pstmt.setString(3, "%" + nombre + "%");
            pstmt.setString(4, "%" + nombre + "%");
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
                    bpg.setFechaEntrega(rs.getString(8));
                    listaPedidos.add(bpg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidos;

    }

    public String verificarestadopedido(int idPedido,int estadoped){
        String ver="no";
        String sql = "SELECT ep.nombre FROM pedido p \n" +
                "inner join estatuspedido ep on (p.idestatuspedido= ep.idestatuspedido)\n" +
                "where p.idpedido=? and p.idestatuspedido= ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, estadoped);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    ver=rs.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ver;
    }


    public void actualizarStockCancelado(ArrayList<BPedidoD> listaprod,int idf){

        for(BPedidoD bPedidoD:listaprod) {
            String sql = "update mydb.productoporfarmacia set stock= stock +"+bPedidoD.getUnidades()+" where idproducto="+bPedidoD.getIdproducto()+" and idfarmacia=?;";

            try (Connection conn = this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.setInt(1, idf);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validarpedidocancelado(int idPedido,int estadoped){
        boolean ver=false;
        String sql = "SELECT ep.nombre FROM pedido p \n" +
                "inner join estatuspedido ep on (p.idestatuspedido= ep.idestatuspedido)\n" +
                "where p.idpedido=? and p.idestatuspedido= ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, estadoped);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    ver=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ver;
    }

    public boolean validarpedidoentregado(int idPedido,int estadoped){
        boolean ver1=false;
        String sql = "SELECT ep.nombre FROM pedido p \n" +
                "inner join estatuspedido ep on (p.idestatuspedido= ep.idestatuspedido)\n" +
                "where p.idpedido=? and p.idestatuspedido= ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, estadoped);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    ver1=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ver1;
    }


}