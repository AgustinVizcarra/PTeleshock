package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.PerfilFarmDao;
import pe.edu.pucp.pteleshock.Dao.VentFechaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Farm_PefilServlet", value = "/Farm_Pefil")
public class Farm_PefilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");

        if (farmacia != null) {

        response.setContentType("text/html");

        PerfilFarmDao pfdao = new PerfilFarmDao();
        VentFechaDao vfdao = new VentFechaDao();

        request.setAttribute("perfilfarm",pfdao.perfilFarmacia());
        request.setAttribute("listventfecha",vfdao.listventasFecha());

        RequestDispatcher view = request.getRequestDispatcher("/Farmacia/perfil_farmacia.jsp");
        view.forward(request,response);

        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
