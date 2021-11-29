package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuario;

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
        HttpSession session = request.getSession();
        BUsuario admin = (BUsuario) session.getAttribute("adminSession");
        if(admin!=null) {
            RequestDispatcher view = request.getRequestDispatcher("/Administracion/buscar_farmacia.jsp");
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
