package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BFarmacia;
import pe.edu.pucp.pteleshock.Dao.Distrfarm_Dao;
import pe.edu.pucp.pteleshock.Dao.FarmaciaDao;
import pe.edu.pucp.pteleshock.Dao.UsuarioDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Admin_ResultServlet", value = "/Admin_Result")
public class Admin_ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        FarmaciaDao farmaciaDao = new FarmaciaDao();
        UsuarioDao usuarioDao = new UsuarioDao();
        Distrfarm_Dao distrfarm_dao = new Distrfarm_Dao();
        String ruc = request.getParameter("ruc") != null ? request.getParameter("ruc") : "";
        BFarmacia farmaciaEncontrada = farmaciaDao.getFarmaciaCompleta(ruc);

        int idDistritoInt = usuarioDao.obtenerUsuario(farmaciaEncontrada.getIdusuario()).getIdDistrito();
        String idDistritoStr = String.valueOf(idDistritoInt);
        String distritoFarmaciaEncontrada = distrfarm_dao.obtenerDistritoPorId(idDistritoStr).getNombre();
        request.setAttribute("distritoFarmaciaEncontrada", distritoFarmaciaEncontrada);
        request.setAttribute("farmaciaEncontrada", farmaciaEncontrada);
        RequestDispatcher view = request.getRequestDispatcher("/Administracion/resultado_farmacia.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
