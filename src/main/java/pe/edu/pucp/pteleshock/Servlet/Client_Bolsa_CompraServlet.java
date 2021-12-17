package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BFarmacia;
import pe.edu.pucp.pteleshock.Beans.BPedidoEstado;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.BolsaCompraDao;
import pe.edu.pucp.pteleshock.Dao.FarmaciaDao;
import pe.edu.pucp.pteleshock.Dao.ProductosFDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Client_Bolsa_CompraServlet", value = "/Client_Bolsa_Compra")
public class Client_Bolsa_CompraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BolsaCompraDao bolsaCompraDao = new BolsaCompraDao();
        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");

        if (cliente != null) {

            String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
            String idFarmaciaStr = request.getParameter("idF") != null ? request.getParameter("idF") : "";
            String idProd = request.getParameter("idProd") != null ? request.getParameter("idProd") : "";
            LocalDateTime hora = LocalDateTime.now();
            DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");

            session.setAttribute("hora-min", hora.format(f));
            ProductosFDao productosFDao = new ProductosFDao();

            switch (action) {
                case "listar":
                    ArrayList<BPedidoEstado> listBolsa1 = (ArrayList<BPedidoEstado>) session.getAttribute("bolsita");

                    String idPedidoStr = session.getAttribute("idPed") != null ? (String) session.getAttribute("idPed") : "";
                    String idProductoStr = session.getAttribute("idProd") != null ? (String) session.getAttribute("idProd") : "";

                    session.removeAttribute("idPed");
                    session.removeAttribute("idProd");

                    FarmaciaDao farmaciaDao = new FarmaciaDao();
                    ArrayList<BFarmacia> listaFarmacia = farmaciaDao.getListaTodasFarmacias("");
                    int cantFarm = listaFarmacia.size();
                    request.setAttribute("listaFarmacia", listaFarmacia);
                    if (!idPedidoStr.equals("")) {
                        listBolsa1.add(bolsaCompraDao.pedidosCarrito(Integer.parseInt(idPedidoStr),Integer.parseInt(idProductoStr)));
                        session.setAttribute("bolsita", listBolsa1);
                    }

                    HashMap<Integer, ArrayList<BPedidoEstado>> map = new HashMap<>();
                    session.setAttribute("map", map);

                    for (int i = 1; i <= cantFarm; i++) {
                        ArrayList<BPedidoEstado> lista = new ArrayList<>();
                        for (BPedidoEstado bp : listBolsa1) {
                            if (bp.getPedido().getIdFarmacia() == i) {
                                lista.add(bp);
                                map.put(bp.getPedido().getIdFarmacia(), lista);
                            }
                        }
                        session.setAttribute("map", map);
                    }

                    if (map.isEmpty()) {
                        session.setAttribute("msg", "No tiene productos agregados a su bolsa de compras");
                    }

                    System.out.println("msg1"+session.getAttribute("msg1"));
                    RequestDispatcher view = request.getRequestDispatcher("/Cliente/bolsa_de_compra.jsp");
                    view.forward(request, response);

                    break;
                case "borrar":
                    String idPedido = request.getParameter("idP") != null ? request.getParameter("idP") : "";
                    BPedidoEstado bped = bolsaCompraDao.obtenerProd(Integer.parseInt(idProd), Integer.parseInt(idPedido), Integer.parseInt(idFarmaciaStr));
                    bolsaCompraDao.eliminarCarrito(Integer.parseInt(idProd), Integer.parseInt(idPedido), Integer.parseInt(idFarmaciaStr));
                    ArrayList<BPedidoEstado> listBolsa2 = (ArrayList<BPedidoEstado>) session.getAttribute("bolsita");
                    HashMap<Integer, Integer> map1 = (HashMap<Integer, Integer>) session.getAttribute("maps");
                    if (bped != null) {
                        for (int i = 0; i < listBolsa2.size(); i++) {
                            BPedidoEstado bped1 = listBolsa2.get(i);
                            if (bped1.getNombreProducto().equals(bped.getNombreProducto())) {
                                //found, delete.
                                listBolsa2.remove(i);
                                map1.remove(bped1.getPedido().getIdFarmacia());
                                break;
                            }
                        }
                        session.setAttribute("maps",map1);
                        session.setAttribute("bolsita", listBolsa2);
                    } else {
                        System.out.println("es nulo");
                    }
                    response.sendRedirect(request.getContextPath() + "/Client_Bolsa_Compra");
                    session.removeAttribute("msg1");
                    break;
                case "cancelar":
                    String codVenta = request.getParameter("idPG") != null ? request.getParameter("idPG") : "";
                    String idPedidoStr1 = request.getParameter("idP") != null ? request.getParameter("idP") : "";
                    String idFarmacoaStr1 = request.getParameter("idF") != null ? request.getParameter("idF") : "";
                    int idF = Integer.parseInt(idFarmacoaStr1);
                    int idP = Integer.parseInt(idPedidoStr1);
                    int cod = Integer.parseInt(codVenta);
                    bolsaCompraDao.cancelarPedido(idP);
                    response.sendRedirect(request.getContextPath() + "/Client_Estado_Pendiente?idPG=" + cod + "&idFPd=" + idF);
                    break;
            }

        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        BolsaCompraDao bolsaCompraDao = new BolsaCompraDao();
        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");

        switch (action) {
            case "actualizarFoto":
                String foto = request.getParameter("fotoReceta") != null ? request.getParameter("fotoReceta") : "";
                String idFarmaciaStr2 = request.getParameter("idF") != null ? request.getParameter("idF") : "";
                String idProd2 = request.getParameter("idProd") != null ? request.getParameter("idProd") : "";
                String idPedido3 = request.getParameter("idP") != null ? request.getParameter("idP") : "";
                bolsaCompraDao.foto(foto, Integer.parseInt(idFarmaciaStr2), Integer.parseInt(idPedido3), Integer.parseInt(idProd2));
                ArrayList<BPedidoEstado> listBolsa4 = (ArrayList<BPedidoEstado>) session.getAttribute("bolsita");
                BPedidoEstado bped3 = bolsaCompraDao.obtenerProd(Integer.parseInt(idProd2), Integer.parseInt(idPedido3), Integer.parseInt(idFarmaciaStr2));
                for (int i = 0; i < listBolsa4.size(); i++) {
                    BPedidoEstado bped2 = listBolsa4.get(i);

                    if (bped2.getNombreProducto().equals(bped3.getNombreProducto())) {
                        listBolsa4.set(i, bped3);
                        session.setAttribute("bolsita", listBolsa4);
                        break;
                    }
                }
                response.sendRedirect(request.getContextPath() + "/Client_Bolsa_Compra");
                break;
            case "actualizar":
                String cantidad = request.getParameter("cant") != null ? request.getParameter("cant") : "";
                String idFarmaciaStr1 = request.getParameter("idF") != null ? request.getParameter("idF") : "";
                String idProd = request.getParameter("idProd") != null ? request.getParameter("idProd") : "";
                String idPedido1 = request.getParameter("idP") != null ? request.getParameter("idP") : "";

                bolsaCompraDao.cantidad(Integer.parseInt(cantidad), Integer.parseInt(idFarmaciaStr1), Integer.parseInt(idPedido1), Integer.parseInt(idProd));
                ArrayList<BPedidoEstado> listBolsa3 = (ArrayList<BPedidoEstado>) session.getAttribute("bolsita");
                BPedidoEstado bped = bolsaCompraDao.obtenerProd(Integer.parseInt(idProd), Integer.parseInt(idPedido1), Integer.parseInt(idFarmaciaStr1));
                for (int i = 0; i < listBolsa3.size(); i++) {
                    BPedidoEstado bped1 = listBolsa3.get(i);

                    if (bped1.getNombreProducto().equals(bped.getNombreProducto())) {
                        listBolsa3.set(i, bped);
                        session.setAttribute("bolsita", listBolsa3);
                        break;
                    }
                }
                String msg1="Debe ingresar la fecha de entrega";
                session.setAttribute("msg1",msg1);
                System.out.println("msg1"+session.getAttribute("msg1"));
                response.sendRedirect(request.getContextPath() + "/Client_Bolsa_Compra");
                break;
            case "agregar":
                String idProdStr = request.getParameter("idProd") != null ? request.getParameter("idProd") : "";
                String cantidadPStr = request.getParameter("cantidad") != null ? request.getParameter("cantidad") : "";
                String idFarmaciaStr = request.getParameter("idFarmacia") != null ? request.getParameter("idFarmacia") : "";
                String receta = request.getParameter("receta") != null ? request.getParameter("receta") : "";

                ArrayList<BPedidoEstado> listaBolsa4 = (ArrayList<BPedidoEstado>) session.getAttribute("bolsita");

                int idFarmacia = Integer.parseInt(idFarmaciaStr);
                int idPedido;

                HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) session.getAttribute("maps");

                if (map.containsKey(idFarmacia)) {
                    System.out.println("entró al if de generar pedido");
                    idPedido = map.get(idFarmacia);
                    if (listaBolsa4.isEmpty()) {
                        HashMap<Integer, Integer> map2 = new HashMap<>();
                        System.out.println("entró al if empty");
                        idPedido = bolsaCompraDao.generarPedidoCarrito(cliente.getIdUsuario());
                        map2.put(idFarmacia, idPedido);
                        session.setAttribute("maps", map2);
                    }
                } else {
                    System.out.println("entró al else de generar pedido");
                    idPedido = bolsaCompraDao.generarPedidoCarrito(cliente.getIdUsuario());
                    map.put(idFarmacia, idPedido);
                    session.setAttribute("maps", map);
                }

                BPedidoEstado producto = bolsaCompraDao.obtenerProd(Integer.parseInt(idProdStr), idPedido, Integer.parseInt(idFarmaciaStr));

                if (producto == null) {
                    session.setAttribute("idPed", Integer.toString(idPedido));
                    session.setAttribute("idProd",idProdStr);
                    bolsaCompraDao.agregarProductoCarrito(idPedido, Integer.parseInt(idProdStr), Integer.parseInt(cantidadPStr), Integer.parseInt(idFarmaciaStr));
                    response.sendRedirect(request.getContextPath() + "/Client_Bolsa_Compra"); //+ "&idF=" + idFarmacia);

                } else {
                    response.sendRedirect(request.getContextPath() + "/Client_Productos_F?idF=" + idFarmacia); //+ "&idF=" + idFarmacia);
                    session.setAttribute("proAgregado", "El producto ya se encuentra en su bolsa de compras");
                }
                break;

            case "comprar":
                int cont = (int) session.getAttribute("contador");

                if (cont != 0) {
                    String recetaStr = request.getParameter("receta") != null ? request.getParameter("receta") : "";
                    String fotoReceta = request.getParameter("fotoReceta") != null ? request.getParameter("fotoReceta") : "";
                    int codigoVenta = (Integer) session.getAttribute("codVenta");
                    if (codigoVenta == 0) {
                        codigoVenta = (int) Math.floor(Math.random() * 4000 + 1000);
                        session.setAttribute("codVenta", codigoVenta);
                    } else {
                        codigoVenta = (Integer) session.getAttribute("codVenta");
                    }
                    HashMap<Integer, String> entrega = new HashMap<>();
                    HashMap<Integer, Double> mapSubtotal = (HashMap<Integer, Double>) session.getAttribute("Subtotal");
                    HashMap<Integer, ArrayList<BPedidoEstado>> mapKey = (HashMap<Integer, ArrayList<BPedidoEstado>>) session.getAttribute("map");
                    ArrayList<Integer> keys = new ArrayList<>();
                    for (Map.Entry<Integer, ArrayList<BPedidoEstado>> ee : mapKey.entrySet()) {
                        keys.add(ee.getKey());
                    }
                    for (int i = 1; i <= cont; i++) {
                        String Ent = request.getParameter("horaEnt" + i) != null ? request.getParameter("horaEnt" + i) : "";
                        entrega.put(keys.get(i - 1), Ent);
                    }
                    for (Map.Entry<Integer, ArrayList<BPedidoEstado>> es : mapKey.entrySet()) {
                        int key = es.getKey();
                        int idPed = es.getValue().get(0).getPedido().getIdPedido();
                        String Ent = entrega.get(key);
                        Double subTotal = mapSubtotal.get(key);
                        bolsaCompraDao.realizarPedido(idPed, Ent, Boolean.parseBoolean(recetaStr), fotoReceta, codigoVenta, subTotal);
                    }

                    session.removeAttribute("bolsita");
                    session.removeAttribute("maps");
                    session.removeAttribute("map");
                    session.removeAttribute("Subtotal");
                    session.removeAttribute("hora-min");
                    session.removeAttribute("codVenta");
                    session.removeAttribute("contador");
                    session.setAttribute("bolsita", new ArrayList<BPedidoEstado>());
                    int codigoVenta1 = 0;
                    session.setAttribute("codVenta", codigoVenta1);
                    HashMap<Integer, Integer> map1 = new HashMap<>();
                    map1.put(0, 0);
                    session.setAttribute("maps", map1);

                }
                response.sendRedirect(request.getContextPath() + "/Client_Listado_Producto");
                break;
        }

    }
}
