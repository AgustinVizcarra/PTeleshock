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

@WebServlet(name = "Farm_Gestionar_PedidosServlet", value = "/Farm_Gestionar_Pedidos")
public class Farm_Gestionar_PedidosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");

        if (farmacia != null) {

            String pag= request.getParameter("pag")!= null? request.getParameter("pag"):"1";

            GPedidoDao gPedidoDao = new GPedidoDao();
            int idF = (Integer) session.getAttribute("idFarmacia");
            int cantPed= gPedidoDao.cantidadPedidos(idF);
            String cantPedStr= String.valueOf(cantPed);


            request.setAttribute("listaPedido",gPedidoDao.listaPedidosPag(idF,pag));
            request.setAttribute("cantPed",cantPedStr);
            RequestDispatcher view = request.getRequestDispatcher("/Farmacia/gestionar_pedidos.jsp");
            view.forward(request,response);


        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String texto = request.getParameter("textoBuscar").trim();
        String pag = request.getParameter("pag") !=null ? request.getParameter("pag"): "1";
        int idF = (Integer) session.getAttribute("idFarmacia");
        GPedidoDao pedidodao=new GPedidoDao();
        if (texto == null) {
            response.sendRedirect(request.getContextPath() + "/Farm_Gestionar_Pedidos");
        } else {
            int cantPed= pedidodao.cantidadPedidosBuscados(idF,texto);
            String cantPedStr= String.valueOf(cantPed);
            request.setAttribute("textbuscar",texto);
            request.setAttribute("cantPed",cantPedStr);
            request.setAttribute("listaPedidosfiltrada", pedidodao.listaPedidosporNombre(idF,pag,texto));
            RequestDispatcher view = request.getRequestDispatcher("/Farmacia/gestionar_pedidos_filtrados.jsp");
            view.forward(request, response);


        }
    }
}
