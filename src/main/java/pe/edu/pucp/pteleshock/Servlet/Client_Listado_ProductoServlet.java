package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.GPedidoDao;
import pe.edu.pucp.pteleshock.Dao.PedidosGeneralDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Client_Listado_ProductoServlet", value = "/Client_Listado_Producto")
public class Client_Listado_ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Cambiar nombre del servlet --> ListaPedidoGeneral

        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");

        String pagStr = request.getParameter("pag") != null ? request.getParameter("pag") : "1";
        int pag = 1;
        try {
            pag = Integer.parseInt(pagStr);
        }catch ( NumberFormatException n){
            n.printStackTrace();
        }

        // Listar pedidos general
        PedidosGeneralDao pedidosGDao = new PedidosGeneralDao();
        int inicio = 0;
        int pedidosxPag = 5;
        int totalPag = (int) Math.ceil((double)pedidosGDao.obtenerNumFilasPG(cliente.getIdUsuario())  /(double) pedidosxPag);

        if (0<pag & pag <= totalPag ) {
            inicio = pag*pedidosxPag - pedidosxPag;
        }

        request.setAttribute("listaPedidosG", pedidosGDao.listarPedidosGeneral(inicio, cliente.getIdUsuario()));
        request.setAttribute("totalPag", totalPag);
        request.setAttribute("pag", pag);



        //Enviar datos al servlet

        response.setContentType("text/html");
        RequestDispatcher view = request.getRequestDispatcher("/Cliente/listado_de_productos.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");

        String texto = request.getParameter("textoBuscar") != null? request.getParameter("textoBuscar").trim(): "" ;


        String pagStr = request.getParameter("pag") != null ? request.getParameter("pag") : "1";
        int pag = 1;
        try {
            pag = Integer.parseInt(pagStr);
        }catch ( NumberFormatException n){
            n.printStackTrace();
        }

        // Listar pedidos general
        PedidosGeneralDao pedidosGDao = new PedidosGeneralDao();
        int inicio = 0;
        int pedidosxPag = 5;
        int totalPag = (int) Math.ceil((double)pedidosGDao.obtenerNumFilasPGBuscador(cliente.getIdUsuario(), texto)/(double) pedidosxPag );

        if (0<pag & pag <= totalPag ) {
            inicio = pag*pedidosxPag - pedidosxPag;
        }

        request.setAttribute("listaPedidosG", pedidosGDao.buscarPedidosGeneral(inicio, cliente.getIdUsuario(),texto));
        request.setAttribute("totalPag", totalPag);
        request.setAttribute("pag", pag);



        //Enviar datos al servlet

        response.setContentType("text/html");
        RequestDispatcher view = request.getRequestDispatcher("/Cliente/listado_de_productos.jsp");
        view.forward(request, response);




    }
}
