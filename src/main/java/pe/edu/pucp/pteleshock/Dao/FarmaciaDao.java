package pe.edu.pucp.pteleshock.Dao;



import pe.edu.pucp.pteleshock.Beans.BFarmacia;

import java.sql.*;
import java.util.ArrayList;

public class FarmaciaDao extends BaseDao {

    public ArrayList<BFarmacia> getListaFarmaciasPorDistrito(String iddistrito, String pag) {

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();
        int pagint = Integer.parseInt(pag);

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "where b.iddistrito = ?\n" +
                     "order by b.nombre limit " + (pagint-1)*4 + ",4;")) {

            pstmt.setString(1, iddistrito);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int idusuario = rs.getInt(1);
                    int idfarmacia = rs.getInt(2);
                    String nombre = rs.getString(3);
                    String ruc = rs.getString(4);
                    String direccion = rs.getString(5);
                    String correo = rs.getString(6);
                    String estado = rs.getString(7);
                    int pedEntregados = 0;
                    int pedPendientes = 0;
                    int pedCancelados = 0;
                    int pedDeseados = 0;

                    try (Connection conn2 = this.getConnection();
                         Statement stmt2 = conn2.createStatement();
                         ResultSet rs2 = stmt2.executeQuery("select d.idfarmacia, a.nombre Estado, count(distinct(b.idpedido)) NroPedidos\n" +
                                 "from estatuspedido a \n" +
                                 "inner join pedido b on a.idestatuspedido=b.idestatuspedido\n" +
                                 "inner join detallepedido c on b.idpedido=c.idpedido\n" +
                                 "inner join farmacia d on d.idfarmacia=c.idfarmacia\n" +
                                 "inner join usuario e on e.idusuario=d.idusuario\n" +
                                 "inner join distrito f on f.iddistrito=e.iddistrito\n" +
                                 "where d.idfarmacia = " + idfarmacia + "\n" +
                                 "group by  d.idfarmacia, a.nombre;")) {

                        while (rs2.next()) {
                            String estadopedido = rs2.getString(2);
                            switch (estadopedido) {
                                case "entregado":
                                    pedEntregados = rs2.getInt(3);
                                    break;
                                case "pendiente":
                                    pedPendientes = rs2.getInt(3);
                                    break;
                                case "cancelado":
                                    pedCancelados = rs2.getInt(3);
                                    break;
                                case "deseado":
                                    pedDeseados = rs2.getInt(3);
                                    break;
                            }
                        }
                    }

                    BFarmacia bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                    listaFarmacias.add(bFarmacia);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener todas las farmacias por distrito");
            e.printStackTrace();
        }
        return listaFarmacias;
    }


    public ArrayList<BFarmacia> getListaTodasFarmacias(String pag) {

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();
        int pagint = Integer.parseInt(pag);

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "order by b.nombre limit " + (pagint - 1) * 4 + ",4;")) {

            while (rs.next()) {

                int idusuario = rs.getInt(1);
                int idfarmacia = rs.getInt(2);
                String nombre = rs.getString(3);
                String ruc = rs.getString(4);
                String direccion = rs.getString(5);
                String correo = rs.getString(6);
                String estado = rs.getString(7);
                int pedEntregados = 0;
                int pedPendientes = 0;
                int pedCancelados = 0;
                int pedDeseados = 0;

                try (Connection conn2 = this.getConnection();
                     Statement stmt2 = conn2.createStatement();
                     ResultSet rs2 = stmt2.executeQuery("select d.idfarmacia, a.nombre Estado, count(distinct(b.idpedido)) NroPedidos\n" +
                             "from estatuspedido a \n" +
                             "inner join pedido b on a.idestatuspedido=b.idestatuspedido\n" +
                             "inner join detallepedido c on b.idpedido=c.idpedido\n" +
                             "inner join farmacia d on d.idfarmacia=c.idfarmacia\n" +
                             "inner join usuario e on e.idusuario=d.idusuario\n" +
                             "inner join distrito f on f.iddistrito=e.iddistrito\n" +
                             "where d.idfarmacia = " + idfarmacia + "\n" +
                             "group by  d.idfarmacia, a.nombre;")) {

                    while (rs2.next()) {
                        String estadopedido = rs2.getString(2);
                        switch (estadopedido) {
                            case "entregado":
                                pedEntregados = rs2.getInt(3);
                                break;
                            case "pendiente":
                                pedPendientes = rs2.getInt(3);
                                break;
                            case "cancelado":
                                pedCancelados = rs2.getInt(3);
                                break;
                            case "deseado":
                                pedDeseados = rs2.getInt(3);
                                break;
                        }
                    }
                }

                BFarmacia bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                listaFarmacias.add(bFarmacia);
            }


        } catch (SQLException e) {
            System.out.println("Error en obtener todas las farmacias");
            e.printStackTrace();
        }
        return listaFarmacias;
    }

    public BFarmacia getFarmacia(String ruc) {
        BFarmacia farmacia = new BFarmacia();
        int idfarmacia = 0;
        int pedPendientes = 0;

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.idusuario, a.idfarmacia, b.nombre, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "where b.ruc = ?;")) {
            pstmt.setString(1, ruc);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int idusuario = rs.getInt(1);
                    idfarmacia = rs.getInt(2);
                    String nombre = rs.getString(3);
                    String estado = rs.getString(4);

                    farmacia.setIdusuario(idusuario);
                    farmacia.setIdfarmacia(idfarmacia);
                    farmacia.setNombre(nombre);
                    farmacia.setEstado(estado);
                    //System.out.println(idfarmacia);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en la busqueda");
            e.printStackTrace();
        }

        try (Connection conn2 = this.getConnection();
             Statement stmt2 = conn2.createStatement();
             ResultSet rs2 = stmt2.executeQuery("select d.idfarmacia, a.nombre Estado, count(distinct(b.idpedido)) NroPedidos\n" +
                     "from estatuspedido a\n" +
                     "inner join pedido b on a.idestatuspedido=b.idestatuspedido\n" +
                     "inner join detallepedido c on b.idpedido=c.idpedido\n" +
                     "inner join farmacia d on d.idfarmacia=c.idfarmacia\n" +
                     "inner join usuario e on e.idusuario=d.idusuario\n" +
                     "inner join distrito f on f.iddistrito=e.iddistrito\n" +
                     "where d.idfarmacia = " + idfarmacia + " and a.nombre= \"pendiente\" group by  d.idfarmacia, a.nombre;")) {

            if (rs2.next()) {
                pedPendientes = rs2.getInt(3);
                farmacia.setPedPendientes(pedPendientes);
            }
        } catch (SQLException e) {
            System.out.println("Error en la busqueda");
            e.printStackTrace();
        }
        return farmacia;
    }

    public BFarmacia getFarmaciaCompleta(String rucBuscar) {

        BFarmacia bFarmacia = null;

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "where b.ruc = ?\n;")) {

            pstmt.setString(1, rucBuscar);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int idusuario = rs.getInt(1);
                    int idfarmacia = rs.getInt(2);
                    String nombre = rs.getString(3);
                    String ruc = rs.getString(4);
                    String direccion = rs.getString(5);
                    String correo = rs.getString(6);
                    String estado = rs.getString(7);
                    int pedEntregados = 0;
                    int pedPendientes = 0;
                    int pedCancelados = 0;
                    int pedDeseados = 0;

                    try (Connection conn2 = this.getConnection();
                         Statement stmt2 = conn2.createStatement();
                         ResultSet rs2 = stmt2.executeQuery("select d.idfarmacia, a.nombre Estado, count(distinct(b.idpedido)) NroPedidos\n" +
                                 "from estatuspedido a \n" +
                                 "inner join pedido b on a.idestatuspedido=b.idestatuspedido\n" +
                                 "inner join detallepedido c on b.idpedido=c.idpedido\n" +
                                 "inner join farmacia d on d.idfarmacia=c.idfarmacia\n" +
                                 "inner join usuario e on e.idusuario=d.idusuario\n" +
                                 "inner join distrito f on f.iddistrito=e.iddistrito\n" +
                                 "where d.idfarmacia = " + idfarmacia + "\n" +
                                 "group by  d.idfarmacia, a.nombre;")) {

                        while (rs2.next()) {
                            String estadopedido = rs2.getString(2);
                            switch (estadopedido) {
                                case "entregado":
                                    pedEntregados = rs2.getInt(3);
                                    break;
                                case "pendiente":
                                    pedPendientes = rs2.getInt(3);
                                    break;
                                case "cancelado":
                                    pedCancelados = rs2.getInt(3);
                                    break;
                                case "deseado":
                                    pedDeseados = rs2.getInt(3);
                                    break;
                            }
                        }
                    }

                    bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener Farmacia Completa");
            e.printStackTrace();
        }
        return bFarmacia;
    }


    public void actualizarFarmaciaBloqueada(BFarmacia farmacia, String razon, String nAdmi) {

        String sql1 = "UPDATE farmacia SET estatus='bloqueado' where idfarmacia = ? ;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql1)) {
            pstmt.setInt(1, farmacia.getIdfarmacia());
            System.out.println(sql1);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en la actualizacion");
            e.printStackTrace();
        }

        int admin_id = 0;
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("Select idusuario from usuario u where u.nombre='" + nAdmi + "';");
            while (rs.next()) {
                admin_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn3 = this.getConnection();
             PreparedStatement pstmt = conn3.prepareStatement("Insert into historialadmin(idusuario, idfarmacia, idaccion, razon, fechahora) values (?,?,'1',?,now());")) {
            pstmt.setInt(1, admin_id);
            pstmt.setInt(2, farmacia.getIdfarmacia());
            pstmt.setString(3, razon);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en el insert");
            e.printStackTrace();
        }

    }

    //Cambio 3/12/2021 --> Eliminación de la paginación
    public boolean existeFarmacia(String ruc) {
        boolean sExiste = false;
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select * from usuario where ruc = ?;")) {
            pstmt.setString(1, ruc);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    sExiste = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sExiste;
    }


    public int cantidadListaTodasFarmacias() {

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "order by b.nombre;")) {

            while (rs.next()) {

                int idusuario = rs.getInt(1);
                int idfarmacia = rs.getInt(2);
                String nombre = rs.getString(3);
                String ruc = rs.getString(4);
                String direccion = rs.getString(5);
                String correo = rs.getString(6);
                String estado = rs.getString(7);
                int pedEntregados = 0;
                int pedPendientes = 0;
                int pedCancelados = 0;
                int pedDeseados = 0;

                BFarmacia bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                listaFarmacias.add(bFarmacia);
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener la cantidad de todas las farmacias");
            e.printStackTrace();
        }
        return listaFarmacias.size();
    }

    public int cantidadListaTodasFarmaciasPorDistrito(String iddistrito) {

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "where b.iddistrito = ?\n" +
                     "order by b.nombre;")) {

            pstmt.setString(1, iddistrito);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int idusuario = rs.getInt(1);
                    int idfarmacia = rs.getInt(2);
                    String nombre = rs.getString(3);
                    String ruc = rs.getString(4);
                    String direccion = rs.getString(5);
                    String correo = rs.getString(6);
                    String estado = rs.getString(7);
                    int pedEntregados = 0;
                    int pedPendientes = 0;
                    int pedCancelados = 0;
                    int pedDeseados = 0;

                    BFarmacia bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                    listaFarmacias.add(bFarmacia);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener la cantidad de todas las farmacias por distrito");
            e.printStackTrace();
        }
        return listaFarmacias.size();
    }

    public int cantidadListaTodasFarmaciasPorDistritoConBusqueda(String iddistrito, String textoBuscar) {

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "where b.iddistrito = ? and lower(b.nombre) like lower(?)\n" +
                     "order by b.nombre;")) {

            pstmt.setString(1, iddistrito);
            pstmt.setString(2,"%" + textoBuscar + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int idusuario = rs.getInt(1);
                    int idfarmacia = rs.getInt(2);
                    String nombre = rs.getString(3);
                    String ruc = rs.getString(4);
                    String direccion = rs.getString(5);
                    String correo = rs.getString(6);
                    String estado = rs.getString(7);
                    int pedEntregados = 0;
                    int pedPendientes = 0;
                    int pedCancelados = 0;
                    int pedDeseados = 0;

                    BFarmacia bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                    listaFarmacias.add(bFarmacia);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener la cantidad de todas las farmacias por distrito con busqueda");
            e.printStackTrace();
        }
        return listaFarmacias.size();
    }

    public int cantidadListaTodasFarmaciasConBusqueda(String textoBuscar) {

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "where lower(b.nombre) like lower(?)\n" +
                     "order by b.nombre;")) {
            pstmt.setString(1, "%" + textoBuscar + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int idusuario = rs.getInt(1);
                    int idfarmacia = rs.getInt(2);
                    String nombre = rs.getString(3);
                    String ruc = rs.getString(4);
                    String direccion = rs.getString(5);
                    String correo = rs.getString(6);
                    String estado = rs.getString(7);
                    int pedEntregados = 0;
                    int pedPendientes = 0;
                    int pedCancelados = 0;
                    int pedDeseados = 0;

                    BFarmacia bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                    listaFarmacias.add(bFarmacia);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener la cantidad de todas las farmacias por distrito con busqueda");
            e.printStackTrace();
        }
        return listaFarmacias.size();
    }

    public ArrayList<BFarmacia> getListaTodasFarmaciasConBusqueda(String pag, String textoBuscar) {

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();
        int pagint = Integer.parseInt(pag);


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "where lower(b.nombre) like ?\n" +
                     "order by b.nombre limit " + (pagint - 1) * 4 + ",4;")) {

            pstmt.setString(1, "%" + textoBuscar + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int idusuario = rs.getInt(1);
                    int idfarmacia = rs.getInt(2);
                    String nombre = rs.getString(3);
                    String ruc = rs.getString(4);
                    String direccion = rs.getString(5);
                    String correo = rs.getString(6);
                    String estado = rs.getString(7);
                    int pedEntregados = 0;
                    int pedPendientes = 0;
                    int pedCancelados = 0;
                    int pedDeseados = 0;

                    try (Connection conn2 = this.getConnection();
                         Statement stmt2 = conn2.createStatement();
                         ResultSet rs2 = stmt2.executeQuery("select d.idfarmacia, a.nombre Estado, count(distinct(b.idpedido)) NroPedidos\n" +
                                 "from estatuspedido a \n" +
                                 "inner join pedido b on a.idestatuspedido=b.idestatuspedido\n" +
                                 "inner join detallepedido c on b.idpedido=c.idpedido\n" +
                                 "inner join farmacia d on d.idfarmacia=c.idfarmacia\n" +
                                 "inner join usuario e on e.idusuario=d.idusuario\n" +
                                 "inner join distrito f on f.iddistrito=e.iddistrito\n" +
                                 "where d.idfarmacia = " + idfarmacia + "\n" +
                                 "group by  d.idfarmacia, a.nombre;")) {

                        while (rs2.next()) {
                            String estadopedido = rs2.getString(2);
                            switch (estadopedido) {
                                case "entregado":
                                    pedEntregados = rs2.getInt(3);
                                    break;
                                case "pendiente":
                                    pedPendientes = rs2.getInt(3);
                                    break;
                                case "cancelado":
                                    pedCancelados = rs2.getInt(3);
                                    break;
                                case "deseado":
                                    pedDeseados = rs2.getInt(3);
                                    break;
                            }
                        }
                    }
                    BFarmacia bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                    listaFarmacias.add(bFarmacia);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en obtener todas las farmacias por busqueda");
            e.printStackTrace();
        }
        return listaFarmacias;
    }

    public ArrayList<BFarmacia> getListaFarmaciasPorDistritoConBusqueda(String iddistrito, String pag, String textoBuscar) {

        ArrayList<BFarmacia> listaFarmacias = new ArrayList<>();
        int pagint = Integer.parseInt(pag);


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.idusuario, a.idfarmacia, b.nombre, b.ruc, a.direccion, b.correo, a.estatus\n" +
                     "from farmacia a\n" +
                     "inner join usuario b on a.idusuario=b.idusuario\n" +
                     "inner join distrito c on c.iddistrito=b.iddistrito\n" +
                     "where b.iddistrito = ? and lower(b.nombre) like lower(?)\n" +
                     "order by b.nombre limit " + (pagint - 1) * 4 + ",4;")) {

            pstmt.setString(1, iddistrito);
            pstmt.setString(2, "%" + textoBuscar + "%");


            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int idusuario = rs.getInt(1);
                    int idfarmacia = rs.getInt(2);
                    String nombre = rs.getString(3);
                    String ruc = rs.getString(4);
                    String direccion = rs.getString(5);
                    String correo = rs.getString(6);
                    String estado = rs.getString(7);
                    int pedEntregados = 0;
                    int pedPendientes = 0;
                    int pedCancelados = 0;
                    int pedDeseados = 0;

                    try (Connection conn2 = this.getConnection();
                         Statement stmt2 = conn2.createStatement();
                         ResultSet rs2 = stmt2.executeQuery("select d.idfarmacia, a.nombre Estado, count(distinct(b.idpedido)) NroPedidos\n" +
                                 "from estatuspedido a \n" +
                                 "inner join pedido b on a.idestatuspedido=b.idestatuspedido\n" +
                                 "inner join detallepedido c on b.idpedido=c.idpedido\n" +
                                 "inner join farmacia d on d.idfarmacia=c.idfarmacia\n" +
                                 "inner join usuario e on e.idusuario=d.idusuario\n" +
                                 "inner join distrito f on f.iddistrito=e.iddistrito\n" +
                                 "where d.idfarmacia = " + idfarmacia + "\n" +
                                 "group by  d.idfarmacia, a.nombre;")) {

                        while (rs2.next()) {
                            String estadopedido = rs2.getString(2);
                            switch (estadopedido) {
                                case "entregado":
                                    pedEntregados = rs2.getInt(3);
                                    break;
                                case "pendiente":
                                    pedPendientes = rs2.getInt(3);
                                    break;
                                case "cancelado":
                                    pedCancelados = rs2.getInt(3);
                                    break;
                                case "deseado":
                                    pedDeseados = rs2.getInt(3);
                                    break;
                            }
                        }
                    }

                    BFarmacia bFarmacia = new BFarmacia(idusuario, idfarmacia, nombre, ruc, direccion, correo, estado, pedEntregados, pedPendientes, pedCancelados, pedDeseados);
                    listaFarmacias.add(bFarmacia);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener todas las farmacias por distrito con busqueda");
            e.printStackTrace();
        }
        return listaFarmacias;
    }

    public int obtenerIdFarmacia(int idUsuario ){

        int idFarmacia=0;
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT idfarmacia FROM farmacia where idusuario=?;")) {

            pstmt.setInt(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    idFarmacia = rs.getInt(1);

                }
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener todas las farmacias por distrito con busqueda");
            e.printStackTrace();
        }
        return idFarmacia;

    }

}

