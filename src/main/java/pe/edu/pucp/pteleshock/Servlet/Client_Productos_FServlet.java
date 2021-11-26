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

        String idFarmaciaStr = request.getParameter("idF")!=null?request.getParameter("idF"):"";
        String pagStr = request.getParameter("pag") != null ? request.getParameter("pag") : "1";
        int pag = 1;
        try {
            pag = Integer.parseInt(pagStr);
        } catch (NumberFormatException n) {
            n.printStackTrace();
        }
        int idFarmacia = Integer.parseInt(idFarmaciaStr);

        ProductosFDao productosFDao = new ProductosFDao();

        // Listar pedidos general | Validación de páginas
        int inicio = 0;
        int pedidosxPag = 6;
        int totalPag = (int) Math.ceil((double) productosFDao.obtenerNumProductos(idFarmaciaStr) / (double) pedidosxPag);
        if (0 < pag & pag <= totalPag) {
            inicio = pag * pedidosxPag - pedidosxPag;
        }


        request.setAttribute("farmacia",productosFDao.obtenerFarmacia(idFarmacia));
        request.setAttribute("listaProductosF",productosFDao.listarProductosF(idFarmacia,inicio));
        request.setAttribute("totalPag", totalPag);
        request.setAttribute("pag", pag);

        RequestDispatcher view = request.getRequestDispatcher("/Cliente/productos_fA.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
