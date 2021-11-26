package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Dao.GPedidoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Farm_Detalles_PedidoServlet", value = "/Farm_Detalles_Pedido")
public class Farm_Detalles_PedidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GPedidoDao gPedidoDao = new GPedidoDao();
        String id = request.getParameter("id");
        int idInt = Integer.parseInt(id);
        request.setAttribute("listaDPedido",gPedidoDao.listaPedidosD(idInt));
        request.setAttribute("listaproducto",gPedidoDao.listaProductos(idInt));
        RequestDispatcher view = request.getRequestDispatcher("/Farmacia/detalles_pedido.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
