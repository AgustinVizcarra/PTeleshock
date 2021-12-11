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

@WebServlet(name = "Admin_ResultServlet", value = "/Admin_Result")
public class Admin_ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        BUsuario admin = (BUsuario) session.getAttribute("adminSession");
        FarmaciaDao farmaciaDao = new FarmaciaDao();

        if(admin!=null) {
            String ruc = request.getParameter("ruc") != null ? request.getParameter("ruc") : "";
            BFarmacia farmaciaEncontrada = farmaciaDao.getFarmaciaCompleta(ruc);
            request.setAttribute("farmaciaEncontrada", farmaciaEncontrada);
            RequestDispatcher view = request.getRequestDispatcher("/Administracion/resultado_farmacia.jsp");
            view.forward(request, response);
        }else{
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
