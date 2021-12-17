package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Dao.PedidoEstadoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Client_Estado_PendienteServlet", value = "/Client_Estado_Pendiente")
public class Client_Estado_PendienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String idFPdStr = request.getParameter("idFPd") != null? request.getParameter("idFPd"):"";
        String idPG = request.getParameter("idPG") != null? request.getParameter("idPG"):"";
        int idFPd = Integer.parseInt(idFPdStr);

        PedidoEstadoDao pedidoEstadoDao = new PedidoEstadoDao();

        request.setAttribute("listaPedidoE",pedidoEstadoDao.listarPedidosEstado(idPG,idFPd));
        request.setAttribute("sePuedeCancelar",pedidoEstadoDao.sePuedeCancelar(idPG,idFPd));

        RequestDispatcher view = request.getRequestDispatcher("/Cliente/estado_de_compra_pendiente.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
