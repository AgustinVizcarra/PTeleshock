package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.PxFarDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Farm_Vista_ProductosServlet", value = "/Farm_Vista_ProductosServlet")
public class Farm_Vista_ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html");

        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");

        if (farmacia != null) {


        String pag = request.getParameter("pag") !=null ? request.getParameter("pag"): "1";

        PxFarDao pxFarDao=new PxFarDao();

        int cant=pxFarDao.cantidadProductosF();
        String cantStr=String.valueOf(cant);
        request.setAttribute("listaxFarmacia",pxFarDao.listarProductosF(pag));

        request.setAttribute("cantProd",cantStr);

        RequestDispatcher view = request.getRequestDispatcher("/Farmacia/visualizar_productos.jsp");
        view.forward(request,response);

        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String texto = request.getParameter("textoBuscar");
        String pag = request.getParameter("pag") !=null ? request.getParameter("pag"): "1";

        PxFarDao pxFarDao=new PxFarDao();
        if (texto == null) {
            response.sendRedirect(request.getContextPath() + "/Farm_Vista_ProductosServlet");
        } else {
            int cant=pxFarDao.cantidadProductosBuscados(texto);
            String cantStr=String.valueOf(cant);
            request.setAttribute("cantProd",cantStr);
            request.setAttribute("textbuscar",texto);

            request.setAttribute("listaxFarmacia", pxFarDao.buscarProductoPorNombre(pag,texto));
            RequestDispatcher view = request.getRequestDispatcher("/Farmacia/visualizar_productosFiltrados.jsp");
            view.forward(request, response);


        }

    }





}
