package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BPedidoEstado;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.BolsaCompraDao;
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
            String idProd= request.getParameter("idProd") != null ? request.getParameter("idProd") : "";
            LocalDateTime hora = LocalDateTime.now();
            DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
            session.setAttribute("hora-min",hora.format(f));

            ProductosFDao productosFDao = new ProductosFDao();
            switch(action){
                case "listar":
                    ArrayList<BPedidoEstado> listBolsa1 = (ArrayList<BPedidoEstado>) session.getAttribute("bolsita");
                    String idPedidoStr = request.getParameter("idPed") != null ? request.getParameter("idPed") : "";
                    if(!idPedidoStr.equals("")) {
                        listBolsa1.add(bolsaCompraDao.pedidosCarrito(Integer.parseInt(idPedidoStr)));
                        session.setAttribute("bolsita", listBolsa1);
                    }
                    HashMap<String, ArrayList<BPedidoEstado>> map = new HashMap<>();
                    session.setAttribute("map",map);
                    for(int i=1;i<=100;i++){
                        ArrayList<BPedidoEstado> lista=new ArrayList<>();
                        for(BPedidoEstado bp: listBolsa1){
                            if(bp.getPedido().getIdFarmacia()==i){
                                lista.add(bp);
                                map.put(bp.getPedido().getNombreFarmacia(),lista);
                            }
                        }
                        session.setAttribute("map",map);
                    }

                    RequestDispatcher view = request.getRequestDispatcher("/Cliente/bolsa_de_compra.jsp");
                    view.forward(request, response);

                    break;
                case "borrar":
                    String idPedido = request.getParameter("idP") != null ? request.getParameter("idP") : "";
                    BPedidoEstado bped=bolsaCompraDao.obtenerProd(Integer.parseInt(idProd),Integer.parseInt(idPedido),Integer.parseInt(idFarmaciaStr));
                    bolsaCompraDao.eliminarProdCarrito(Integer.parseInt(idProd),Integer.parseInt(idPedido),Integer.parseInt(idFarmaciaStr));
                    ArrayList <BPedidoEstado> listBolsa2= (ArrayList <BPedidoEstado>) session.getAttribute("bolsita");

                    if(bped != null){
                       System.out.println(bped.getNombreProducto());
                       System.out.println(listBolsa2.get(0).getNombreProducto());
                        for(int i = 0; i < listBolsa2.size(); i++) {
                            BPedidoEstado bped1 = listBolsa2.get(i);

                            if (bped1.getNombreProducto().equals(bped.getNombreProducto())) {
                                //found, delete.
                                listBolsa2.remove(i);
                                ;
                                break;
                            }
                        }
                       System.out.println(listBolsa2.get(listBolsa2.size()-1).getNombreProducto());
                       session.setAttribute("bolsita",listBolsa2);
                   }else{
                        System.out.println("es nulo");
                    }
                    response.sendRedirect(request.getContextPath() + "/Client_Bolsa_Compra");
                    break;
                case "cancelar":
                    String idPedidoStr1 = request.getParameter("idPed") != null ? request.getParameter("idPed") : "";
                    bolsaCompraDao.cancelarPedido(Integer.parseInt(idPedidoStr1));
                    RequestDispatcher view1 = request.getRequestDispatcher("/Cliente/bolsa_de_compra.jsp");
                    view1.forward(request, response);
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
            case "agregar":
                String idProdStr = request.getParameter("idProd") != null ? request.getParameter("idProd") : "";
                String cantidadPStr = request.getParameter("cantidad") != null ? request.getParameter("cantidad") : "";
                String idFarmaciaStr = request.getParameter("idFarmacia") != null ? request.getParameter("idFarmacia") : "";
                String receta = request.getParameter("receta") != null ? request.getParameter("receta") : "";

                int idFarmacia = Integer.parseInt(idFarmaciaStr);
                int idPedido;
                HashMap<Integer, Integer> map= (HashMap<Integer, Integer>) session.getAttribute("maps");
                if(map.containsKey(idFarmacia)){
                    idPedido=map.get(idFarmacia);
                }else{
                    idPedido = bolsaCompraDao.generarPedidoCarrito(cliente.getIdUsuario());
                    map.put(idFarmacia,idPedido);
                    session.setAttribute("maps",map);
                }

                for (Integer key: map.keySet()){
                    System.out.println(key+ " = " + map.get(key));
                }

                bolsaCompraDao.agregarProductoCarrito(idPedido, Integer.parseInt(idProdStr), Integer.parseInt(cantidadPStr), Integer.parseInt(idFarmaciaStr));
                response.sendRedirect(request.getContextPath() + "/Client_Bolsa_Compra?idPed=" + idPedido); //+ "&idF=" + idFarmacia);
                break;

            case "comprar":
                String idPedidoC = request.getParameter("idPedido") != null ? request.getParameter("idPedido") : "";
                String fechaEnt = request.getParameter("fechaEnt") != null ? request.getParameter("fechaEnt") : "";
                String horaEnt = request.getParameter("horaEnt") != null ? request.getParameter("horaEnt") : "";
                String recetaStr = request.getParameter("receta") != null ? request.getParameter("receta") : "";
                String fotoReceta = request.getParameter("fotoReceta") != null ? request.getParameter("fotoReceta") : "";
                int codigoVenta= (Integer) session.getAttribute("codVenta");
                if(codigoVenta==0) {
                    codigoVenta=(int) Math.floor(Math.random()*4000 + 1000);
                    session.setAttribute("codVenta",codigoVenta);
                }else{
                    codigoVenta= (Integer) session.getAttribute("codVenta");
                }
                bolsaCompraDao.realizarPedido(Integer.parseInt(idPedidoC), fechaEnt, horaEnt, Boolean.parseBoolean(recetaStr), fotoReceta,codigoVenta);
                response.sendRedirect(request.getContextPath() + "/Client_Listado_Producto");

                break;
        }

    }
}
