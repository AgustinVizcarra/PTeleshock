package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.GPedidoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Farm_Detalles_PedidoServlet", value = "/Farm_Detalles_Pedido")
public class Farm_Detalles_PedidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

            String pag= request.getParameter("pag")!= null? request.getParameter("pag"):"1";

            int idF = (Integer) session.getAttribute("idFarmacia");
            GPedidoDao gPedidoDao = new GPedidoDao();
            String id = request.getParameter("id")!= null? request.getParameter("id"):"nohay";

            if(id.equals("nohay")){

                response.sendRedirect(request.getContextPath() + "/Farm_Gestionar_Pedidos");
            }else{
                if(id.length()<10 && id!=null && !(id.isEmpty()) ){

                    boolean isIdNumeric =  false;

                    if(!(id.equals(""))){
                        isIdNumeric =  id.matches("[+-]?\\d*(\\.\\d+)?");
                    }

                    if(isIdNumeric){
                        int idInt = Integer.parseInt(id);
                        session.setAttribute("idpedido",idInt);
                        request.setAttribute("listaDPedido",gPedidoDao.listaPedidosD(idF,idInt));
                        request.setAttribute("listaproducto",gPedidoDao.listaProductospag(idF,idInt,pag));
                        int cantPed=gPedidoDao.cantidadProductos1(idF,idInt);
                        String cantPedStr= String.valueOf(cantPed);
                        request.setAttribute("cantPed",cantPedStr);
                        RequestDispatcher view = request.getRequestDispatcher("/Farmacia/detalles_pedido.jsp");
                        view.forward(request,response);

                    }else{
                        response.sendRedirect(request.getContextPath() + "/Farm_Gestionar_Pedidos");
                    }

                }else{

                    response.sendRedirect(request.getContextPath() + "/Farm_Gestionar_Pedidos");

                }
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
