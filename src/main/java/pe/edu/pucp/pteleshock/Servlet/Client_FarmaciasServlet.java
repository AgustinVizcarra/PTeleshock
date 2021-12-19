package pe.edu.pucp.pteleshock.Servlet;


import pe.edu.pucp.pteleshock.Beans.BDistristos;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.FarmaciasPorDistritoDao;
import pe.edu.pucp.pteleshock.Dao.ListaDistritosDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Client_FarmaciasServlet", value = "/Client_Farmacias")
public class Client_FarmaciasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");
        String idDistritoStr = request.getParameter("idD") != null ? request.getParameter("idD") : String.valueOf(cliente.getIdDistrito());
        String pagStr = request.getParameter("pag") != null ? request.getParameter("pag") : "1";
        String nombreFarmaciaBuscar = request.getParameter("nombreFarmacia") != null ? request.getParameter("nombreFarmacia") : "";

        int idDistrito = cliente.getIdDistrito();
        int pag = 1;
        try {
            pag = Integer.parseInt(pagStr);
            idDistrito = Integer.parseInt(idDistritoStr);
        } catch (NumberFormatException n) {
            n.printStackTrace();
        }

        // Listar farmacias por distrito
        FarmaciasPorDistritoDao fxDao = new FarmaciasPorDistritoDao();
        // Listar distritos
        ListaDistritosDao listaDistritosDao = new ListaDistritosDao();

        BDistristos distrito =listaDistritosDao.obtenerDistritoPorId(idDistrito);
        if (distrito == null){
            response.sendRedirect(request.getContextPath() + "/Client_Farmacias");
        }else {

            // Listar pedidos general | Validación de páginas
            int inicio = 0;
            int pedidosxPag = 6;
            int totalPag = (int) Math.ceil((double) fxDao.obtenerNumFarmacias(idDistritoStr, nombreFarmaciaBuscar) / (double) pedidosxPag);
            if (0 < pag & pag <= totalPag) {
                inicio = pag * pedidosxPag - pedidosxPag;
            }

            request.setAttribute("listaxFarmacias", fxDao.listarFarmaciasPorDistritoConBusqueda(idDistrito, inicio, nombreFarmaciaBuscar));
            request.setAttribute("nombreFarmacia", nombreFarmaciaBuscar);
            request.setAttribute("totalPag", totalPag);
            request.setAttribute("pag", pag);


            //Enviar datos al servlet
            request.setAttribute("listaDistritos", listaDistritosDao.listarDistritos());
            request.setAttribute("distrito", listaDistritosDao.obtenerDistritoPorId(idDistrito));
            System.out.println(listaDistritosDao.obtenerDistritoPorId(idDistrito));

            RequestDispatcher view = request.getRequestDispatcher("/Cliente/farmacias.jsp");
            view.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");

        String idDistritoStr = request.getParameter("idD") != null ? request.getParameter("idD") : String.valueOf(cliente.getIdDistrito());

        int idDistrito = cliente.getIdDistrito();
        try {
            idDistrito = Integer.parseInt(idDistritoStr);
        } catch (NumberFormatException n) {
            n.printStackTrace();
        }

        String nombreFarmaciaBuscar = request.getParameter("nombreFarmacia") != null ? request.getParameter("nombreFarmacia") : "";

        if (nombreFarmaciaBuscar.equals("")) {
            response.sendRedirect(request.getContextPath() + "/Client_Farmacias?idD=" + idDistrito);
        } else {
            response.sendRedirect(request.getContextPath() + "/Client_Farmacias?idD=" + idDistrito + "&nombreFarmacia=" + nombreFarmaciaBuscar);
        }

    }
}
