package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Dao.BolsaCompraDao;
import pe.edu.pucp.pteleshock.Dao.ProductosFDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Client_Bolsa_CompraServlet", value = "/Client_Bolsa_Compra")
public class Client_Bolsa_CompraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idPedidoStr = request.getParameter("idP") != null ? request.getParameter("idP") : "";
        String idFarmaciaStr = request.getParameter("idF") != null ? request.getParameter("idF") : "";


        BolsaCompraDao bolsaCompraDao = new BolsaCompraDao();
        ProductosFDao productosFDao = new ProductosFDao();


        request.setAttribute("listaPedidoCarrito", bolsaCompraDao.listarPedidosCarrito(Integer.parseInt(idPedidoStr)));
        request.setAttribute("farmacia", productosFDao.obtenerFarmacia(Integer.parseInt(idFarmaciaStr)));


        response.setContentType("text/html");
        RequestDispatcher view = request.getRequestDispatcher("/Cliente/bolsa_de_compra.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        BolsaCompraDao bolsaCompraDao = new BolsaCompraDao();


        switch (action) {
            case "agregar":
                String idProdStr = request.getParameter("idProd") != null ? request.getParameter("idProd") : "";
                String cantidadPStr = request.getParameter("cantidad") != null ? request.getParameter("cantidad") : "";
                String idFarmaciaStr = request.getParameter("idFarmacia") != null ? request.getParameter("idFarmacia") : "";
                String receta = request.getParameter("receta") != null ? request.getParameter("receta") : "";


                int idPedido = bolsaCompraDao.generarPedidoCarrito();
                int idFarmacia = Integer.parseInt(idFarmaciaStr);


                bolsaCompraDao.agregarProductoCarrito(idPedido, Integer.parseInt(idProdStr), Integer.parseInt(cantidadPStr), Integer.parseInt(idFarmaciaStr));

                response.sendRedirect(request.getContextPath() + "/Client_Bolsa_Compra?idP=" + idPedido + "&idF=" + idFarmacia);
                break;
            case "comprar":
                String idPedidoC = request.getParameter("idPedido") != null ? request.getParameter("idPedido") : "";
                String fechaEnt = request.getParameter("fechaEnt") != null ? request.getParameter("fechaEnt") : "";
                String horaEnt = request.getParameter("horaEnt") != null ? request.getParameter("horaEnt") : "";
                String recetaStr = request.getParameter("receta") != null ? request.getParameter("receta") : "";
                String fotoReceta = request.getParameter("fotoReceta") != null ? request.getParameter("fotoReceta") : "";

                bolsaCompraDao.realizarPedido(Integer.parseInt(idPedidoC),fechaEnt,horaEnt,Boolean.parseBoolean(recetaStr),fotoReceta);

                response.sendRedirect(request.getContextPath() + "/Client_Listado_Producto");

                break;
        }

    }
}
