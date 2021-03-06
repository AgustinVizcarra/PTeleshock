package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Dao.ProductosFDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Client_Detalles_ProductoServlet", value = "/Client_Detalles_Producto")
public class Client_Detalles_ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String idProductoStr = request.getParameter("idP")!=null?request.getParameter("idP"):"";
        String idFarmaciaStr = request.getParameter("idF")!=null?request.getParameter("idF"):"";
        int idProducto;
        int idFarmacia;

        try {
            idProducto = Integer.parseInt(idProductoStr);
            idFarmacia = Integer.parseInt(idFarmaciaStr);
            ProductosFDao productosFDao = new ProductosFDao();

            if (productosFDao.detalleProductoF(idFarmacia,idProducto) == null || productosFDao.obtenerFarmacia(idFarmacia)==null){
                response.sendRedirect(request.getContextPath() + "/Client_Productos_F?idF" + idFarmacia);
            }else {
                request.setAttribute("detProd",productosFDao.detalleProductoF(idFarmacia,idProducto));
                request.setAttribute("farmacia",productosFDao.obtenerFarmacia(idFarmacia));
                RequestDispatcher view = request.getRequestDispatcher("/Cliente/detalles_producto.jsp");
                view.forward(request,response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Client_Productos_F?idF=" + idFarmaciaStr);
        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
