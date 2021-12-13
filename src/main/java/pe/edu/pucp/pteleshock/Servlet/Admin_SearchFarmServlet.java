package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BFarmacia;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.FarmaciaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Admin_SearchFarmServlet", value = "/Admin_SearchFarm")
public class Admin_SearchFarmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String ruc = request.getParameter("ruc") != null ? request.getParameter("ruc") : "";
        String mensaje = request.getParameter("mensaje") != null ? request.getParameter("mensaje") : "";

        request.setAttribute("mensaje", mensaje);
        request.setAttribute("rucListado", ruc);
        RequestDispatcher view = request.getRequestDispatcher("/Administracion/buscar_farmacia.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String ruc = request.getParameter("ruc") != null ? request.getParameter("ruc") : "";

        FarmaciaDao farmaciaDao = new FarmaciaDao();
        String mensaje = "";
        boolean campos_nulos = false;

        if (request.getParameter("ruc").isEmpty()) {
            campos_nulos = true;
            mensaje = "Usted envio campos nulos, por favor intente de nuevo";
        }

        if (!campos_nulos) {
            if (farmaciaDao.existeFarmacia(ruc)) {
                response.sendRedirect(request.getContextPath() + "/Admin_Result?ruc=" + ruc);
            } else {
                mensaje = "El RUC introducido no existe o se ha introducido un nombre incorrecto";
                response.sendRedirect(request.getContextPath() + "/Admin_SearchFarm?mensaje=" + mensaje + "&ruc=" + ruc);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/Admin_SearchFarm?mensaje=" + mensaje + "&ruc=" + ruc);
        }
        //System.out.println(mensaje);



    }
}
