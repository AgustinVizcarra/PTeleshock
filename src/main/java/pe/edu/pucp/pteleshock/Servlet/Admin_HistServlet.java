package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Dao.Afarm_Dao;
import pe.edu.pucp.pteleshock.Dao.Bfarm_Dao;
import pe.edu.pucp.pteleshock.Dao.Dfarm_Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Admin_HistServlet", value = "/Admin_Hist")
public class Admin_HistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Afarm_Dao afarm_dao = new Afarm_Dao();
        Bfarm_Dao bfarm_dao = new Bfarm_Dao();
        Dfarm_Dao dfarm_dao = new Dfarm_Dao();
        request.setAttribute("anadidas",afarm_dao.listar_anadidas());
        request.setAttribute("bloqueadas",bfarm_dao.listar_bloqueadas());
        request.setAttribute("desbloqueadas",dfarm_dao.listar_desbloqueadas());
        response.setContentType("text/html");
        RequestDispatcher view = request.getRequestDispatcher("/Administracion/historial_admin.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
