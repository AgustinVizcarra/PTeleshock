package pe.edu.pucp.pteleshock.Servlet;


import pe.edu.pucp.pteleshock.Beans.BDistrito;
import pe.edu.pucp.pteleshock.Beans.BFarmacia;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.Distrfarm_Dao;
import pe.edu.pucp.pteleshock.Dao.FarmaciaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Admin_Farm_ListServlet", value = "/Admin_Farm_List")
public class
Admin_Farm_ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        FarmaciaDao farmaciaDao = new FarmaciaDao();
        Distrfarm_Dao distrfarm_dao = new Distrfarm_Dao();
        ArrayList<BFarmacia> listaFarmacias;
        BDistrito bDistrito;
        String iddistrito = request.getParameter("iddistrito");
        String pag = request.getParameter("pag") != null ? request.getParameter("pag") : "1";
        if (pag.equalsIgnoreCase("")){
            pag = "1";
        }
        int filtrar;
        int cant;
        try {
            if (iddistrito == null) {
                listaFarmacias = farmaciaDao.getListaTodasFarmacias(pag);
                cant = farmaciaDao.cantidadListaTodasFarmacias();
                bDistrito = distrfarm_dao.obtenerDistritoPorId("1");
                filtrar = 0;
            } else {
                listaFarmacias = farmaciaDao.getListaFarmaciasPorDistrito(iddistrito, pag);
                filtrar = 1;
                cant = farmaciaDao.cantidadListaTodasFarmaciasPorDistrito(iddistrito);
                bDistrito = distrfarm_dao.obtenerDistritoPorId(iddistrito);
            }
            if (listaFarmacias.size() > 0) {
                request.setAttribute("listaFarmacias", listaFarmacias);
                request.setAttribute("pag", pag);
                request.setAttribute("filtrar", filtrar);
                request.setAttribute("distrito", bDistrito);
                request.setAttribute("cantidad", cant);

                RequestDispatcher view = request.getRequestDispatcher("/Administracion/listado.jsp");
                view.forward(request, response);
            } else {
                request.setAttribute("distritos", distrfarm_dao.listar_distritos());
                RequestDispatcher view = request.getRequestDispatcher("/Administracion/index_admin.jsp");
                view.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("distritos", distrfarm_dao.listar_distritos());
            RequestDispatcher view = request.getRequestDispatcher("/Administracion/index_admin.jsp");
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String texto = request.getParameter("textoBuscar");
        String iddistrito = request.getParameter("iddistrito");
        String pag = request.getParameter("pag") != null ? request.getParameter("pag") : "1";
        if (pag.equalsIgnoreCase("")){
            pag = "1";
        }
        int filtrar;
        int cant;

        FarmaciaDao farmaciaDao = new FarmaciaDao();
        Distrfarm_Dao distrfarm_dao = new Distrfarm_Dao();
        ArrayList<BFarmacia> listaFarmacias;
        BDistrito bDistrito;

        if (texto == null) {
            if (iddistrito == null) {
                response.sendRedirect(request.getContextPath() + "/Admin_Farm_List");
            } else {
                response.sendRedirect(request.getContextPath() + "/Admin_Farm_List?iddistrito=" + iddistrito);
            }
        } else {
            if (iddistrito == null) {
                listaFarmacias = farmaciaDao.getListaTodasFarmaciasConBusqueda(pag, texto);
                cant = farmaciaDao.cantidadListaTodasFarmaciasConBusqueda(texto);
                bDistrito = distrfarm_dao.obtenerDistritoPorId("1");
                filtrar = 0;

            } else {
                listaFarmacias = farmaciaDao.getListaFarmaciasPorDistritoConBusqueda(iddistrito, pag, texto);
                filtrar = 1;
                cant = farmaciaDao.cantidadListaTodasFarmaciasPorDistritoConBusqueda(iddistrito, texto);
                bDistrito = distrfarm_dao.obtenerDistritoPorId(iddistrito);
            }
            request.setAttribute("pag", pag);
            request.setAttribute("listaFarmacias", listaFarmacias);
            request.setAttribute("filtrar", filtrar);
            request.setAttribute("distrito", bDistrito);
            request.setAttribute("cantidad", cant);
            request.setAttribute("textoBusqueda", texto);
            RequestDispatcher view = request.getRequestDispatcher("/Administracion/listado.jsp");
            view.forward(request, response);
        }
    }
}

