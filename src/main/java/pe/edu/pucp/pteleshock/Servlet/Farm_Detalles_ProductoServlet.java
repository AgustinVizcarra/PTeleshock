package pe.edu.pucp.pteleshock.Servlet;


import pe.edu.pucp.pteleshock.Beans.BListaPFarmacia;
import pe.edu.pucp.pteleshock.Dao.DetProdDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Farm_Detalles_ProductoServlet", value = "/Farm_Detalles_Producto")
public class Farm_Detalles_ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");



        String prod = request.getParameter("prod");

        DetProdDao dpdao = new DetProdDao();



        String action = request.getParameter("action") !=null ? request.getParameter("action"): "detalles";
        switch (action){
            case "borrar":
                BListaPFarmacia exProd = dpdao.existeProParaEliminar(prod);

                int idproducto= Integer.parseInt(prod);
                if (exProd != null) {
                    request.setAttribute("listadetallesP",dpdao.listadetallesP(prod));
                    request.setAttribute("idp",prod);
                    request.setAttribute("valor","noborr");
                    RequestDispatcher view = request.getRequestDispatcher("/Farmacia/detalles_producto.jsp");
                    view.forward(request,response);
                } else {
                    try {

                        dpdao.borrarProducto(idproducto);

//                        response.sendRedirect(request.getContextPath() + "/Farm_Vista_ProductosServlet");
                        request.setAttribute("listadetallesP",dpdao.listadetallesP(prod));
                        request.setAttribute("idp",prod);
                        request.setAttribute("valor","borr");
                        RequestDispatcher view = request.getRequestDispatcher("/Farmacia/detalles_producto.jsp");
                        view.forward(request,response);
                    } catch (SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/Farm_Vista_ProductosServlet");
                        e.printStackTrace();
                    }

                }
                break;
            case "detalles":
                request.setAttribute("listadetallesP",dpdao.listadetallesP(prod));
                request.setAttribute("idp",prod);
                RequestDispatcher view = request.getRequestDispatcher("/Farmacia/detalles_producto.jsp");
                view.forward(request,response);
                break;
        }


    }

}
