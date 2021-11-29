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
            String nombreFarmaciaBuscar = request.getParameter("nombreFarmacia") != null ? request.getParameter("nombreFarmacia") : "";

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
            int totalPag = (int) Math.ceil((double) fxDao.obtenerNumFarmacias(idDistritoStr, nombreFarmaciaBuscar) / (double) pedidosxPag);
            if (0 < pag & pag <= totalPag) {
                inicio = pag * pedidosxPag - pedidosxPag;
            }
            //Enviar datos al servlet
            //System.out.println("Id distrito: "+idDistritoStr);
            //System.out.println("parseado: "+idDistrito);
            //System.out.println("inicio: "+inicio);
            System.out.println("cantidad de farmacias: " + fxDao.listarFarmaciasPorDistritoConBusqueda(idDistrito, inicio, nombreFarmaciaBuscar).size());
            //aqui esta el error
            if (fxDao.listarFarmaciasPorDistrito(idDistrito, inicio).isEmpty()) {
                idDistrito = 18;
                //default option
            }
            System.out.println("cantidad de farmacias luego de default: " + fxDao.listarFarmaciasPorDistritoConBusqueda(idDistrito, inicio, nombreFarmaciaBuscar).size());
            request.setAttribute("listaxFarmacias", fxDao.listarFarmaciasPorDistritoConBusqueda(idDistrito, inicio, nombreFarmaciaBuscar));
            request.setAttribute("nombreFarmacia", nombreFarmaciaBuscar);
            request.setAttribute("totalPag", totalPag);
            request.setAttribute("pag", pag);
            // Listar distritos
            ListaDistritosDao listaDistritosDao = new ListaDistritosDao();

            //Enviar datos al servlet
            //System.out.println("cantidad de Distritos: "+listaDistritosDao.listarDistritos().size());
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
        String nombreFarmaciaBuscar = request.getParameter("nombreFarmacia") != null ? request.getParameter("nombreFarmacia") : "";

        response.sendRedirect(request.getContextPath() + "/Client_Farmacias?idD=" + idDistrito + "&nombreFarmacia=" + nombreFarmaciaBuscar);

    }
}
