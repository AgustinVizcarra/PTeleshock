package pe.edu.pucp.pteleshock.Servlet;


import pe.edu.pucp.pteleshock.Dao.ProductosFDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Client_Productos_FServlet", value = "/Client_Productos_F")
public class Client_Productos_FServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String nombreProductoBuscar = request.getParameter("nombreProducto") != null ? request.getParameter("nombreProducto") : "";
        String idFarmaciaStr = request.getParameter("idF") != null ? request.getParameter("idF") : "";
        int idFarmacia = 0;
        String pagStr = request.getParameter("pag") != null ? request.getParameter("pag") : "1";
        int pag = 1;
        try {
            idFarmacia = Integer.parseInt(idFarmaciaStr);
            pag = Integer.parseInt(pagStr);
        } catch (NumberFormatException n) {
            n.printStackTrace();
            System.out.println("´Problemas con los parámetros");
        }

        ProductosFDao productosFDao = new ProductosFDao();

        // Listar pedidos general | Validación de páginas
        int inicio = 0;
        int pedidosxPag = 8;
        int totalPag = (int) Math.ceil((double) productosFDao.obtenerNumProductos(idFarmaciaStr, nombreProductoBuscar) / (double) pedidosxPag);
        if (0 < pag & pag <= totalPag) {
            inicio = pag * pedidosxPag - pedidosxPag;
        }


        request.setAttribute("farmacia", productosFDao.obtenerFarmacia(idFarmacia));
        request.setAttribute("listaProductosF", productosFDao.listarProductosF(idFarmacia, inicio, nombreProductoBuscar));
        request.setAttribute("totalPag", totalPag);
        request.setAttribute("pag", pag);
        request.setAttribute("nombreProducto",nombreProductoBuscar);

        RequestDispatcher view = request.getRequestDispatcher("/Cliente/productos_fA.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idFarmaciaStr = request.getParameter("idF") != null ? request.getParameter("idF") : "0";
        int idFarmacia = Integer.parseInt(idFarmaciaStr);
        String nombreProductoBuscar = request.getParameter("nombreProducto") != null ? request.getParameter("nombreProducto") : "";

        response.sendRedirect(request.getContextPath() + "/Client_Productos_F?idF=" + idFarmacia + "&nombreProducto=" + nombreProductoBuscar);

    }
}
