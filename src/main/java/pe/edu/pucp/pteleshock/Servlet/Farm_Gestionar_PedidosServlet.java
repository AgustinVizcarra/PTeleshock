package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.GPedidoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Farm_Gestionar_PedidosServlet", value = "/Farm_Gestionar_Pedidos")
public class Farm_Gestionar_PedidosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pag= request.getParameter("pag")!= null? request.getParameter("pag"):"1";

        GPedidoDao gPedidoDao = new GPedidoDao();

        int cantPed= gPedidoDao.cantidadPedidos();
        String cantPedStr= String.valueOf(cantPed);


        request.setAttribute("listaPedido",gPedidoDao.listaPedidosPag(pag));
        request.setAttribute("cantPed",cantPedStr);
        RequestDispatcher view = request.getRequestDispatcher("/Farmacia/gestionar_pedidos.jsp");
        view.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String texto = request.getParameter("textoBuscar");

        GPedidoDao pedidodao=new GPedidoDao();
        if (texto == null) {
            response.sendRedirect(request.getContextPath() + "/Farm_Gestionar_Pedidos");
        } else {

            request.setAttribute("textbuscar",texto);

            request.setAttribute("listaPedidosfiltrada", pedidodao.listaPedidosporNombre(texto));
            RequestDispatcher view = request.getRequestDispatcher("/Farmacia/gestionar_pedidos_filtrados.jsp");
            view.forward(request, response);


        }
    }
}
