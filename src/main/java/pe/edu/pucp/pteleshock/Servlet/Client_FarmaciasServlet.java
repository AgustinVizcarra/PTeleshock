package pe.edu.pucp.pteleshock.Servlet;


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

        if (cliente != null) {

            String idDistritoStr = request.getParameter("idD") != null ? request.getParameter("idD") : String.valueOf(cliente.getIdDistrito());
            String pagStr = request.getParameter("pag") != null ? request.getParameter("pag") : "1";

            int idDistrito = Integer.parseInt(idDistritoStr);
            int pag = 1;
            try {
                pag = Integer.parseInt(pagStr);
            } catch (NumberFormatException n) {
                n.printStackTrace();
            }

            // Listar farmacias por distrito
            FarmaciasPorDistritoDao fxDao = new FarmaciasPorDistritoDao();

            // Listar pedidos general | Validación de páginas
            int inicio = 0;
            int pedidosxPag = 6;
            int totalPag = (int) Math.ceil((double) fxDao.obtenerNumFarmacias(idDistritoStr) / (double) pedidosxPag);
            if (0 < pag & pag <= totalPag) {
                inicio = pag * pedidosxPag - pedidosxPag;
            }

            //Enviar datos al servlet
            request.setAttribute("listaxFarmacias", fxDao.listarFarmaciasPorDistrito(idDistrito, inicio));
            request.setAttribute("totalPag", totalPag);
            request.setAttribute("pag", pag);


            // Listar distritos
            ListaDistritosDao listaDistritosDao = new ListaDistritosDao();

            //Enviar datos al servlet
            request.setAttribute("listaDistritos", listaDistritosDao.listarDistritos());
            request.setAttribute("idDis", idDistritoStr);

            RequestDispatcher view = request.getRequestDispatcher("/Cliente/farmacias.jsp");
            view.forward(request, response);
        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idDistritoStr = request.getParameter("idD") != null ? request.getParameter("idD") : "18";
        int idDistrito = Integer.parseInt(idDistritoStr);

        response.sendRedirect(request.getContextPath() + "/Client_Farmacias?idD=" + idDistrito);

    }
}
