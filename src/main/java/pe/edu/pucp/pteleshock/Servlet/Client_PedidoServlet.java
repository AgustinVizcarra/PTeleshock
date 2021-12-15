package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.PedidoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Client_PedidoServlet", value = "/Client_Pedido")
public class Client_PedidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");

        String idPG = request.getParameter("idPG") != null ? request.getParameter("idPG") : "";
        String pagStr = request.getParameter("pag") != null ? request.getParameter("pag") : "1";
        int pag = 1;
        try {
            pag = Integer.parseInt(pagStr);
        } catch (NumberFormatException n) {
            n.printStackTrace();
        }

        // Listar pedidos general | Validación de páginas
        PedidoDao pedidoDao = new PedidoDao();
        int inicio = 0;
        int pedidosxPag = 3;
        int totalPag = (int) Math.ceil((double) pedidoDao.obtenerNumPedidos(idPG,cliente.getIdUsuario()) / (double) pedidosxPag);
        if (0 < pag & pag <= totalPag) {
            inicio = pag * pedidosxPag - pedidosxPag;
        }

        //Enviar datos al servlet
        request.setAttribute("listaPedidos", pedidoDao.listarPedidos(idPG,inicio));
        request.setAttribute("totalPag", totalPag);
        request.setAttribute("pag", pag);

        response.setContentType("text/html");
        RequestDispatcher view = request.getRequestDispatcher("/Cliente/pedido.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
