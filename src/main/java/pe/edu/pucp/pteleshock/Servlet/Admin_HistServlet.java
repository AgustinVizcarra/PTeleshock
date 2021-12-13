package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.Afarm_Dao;
import pe.edu.pucp.pteleshock.Dao.Bfarm_Dao;
import pe.edu.pucp.pteleshock.Dao.Dfarm_Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Admin_HistServlet", value = "/Admin_Hist")
public class Admin_HistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        Afarm_Dao afarm_dao = new Afarm_Dao();
        Bfarm_Dao bfarm_dao = new Bfarm_Dao();
        Dfarm_Dao dfarm_dao = new Dfarm_Dao();
        String action = request.getParameter("action")==null?"":request.getParameter("action");
        System.out.println("accion: "+action);
        switch (action){
            case "add":
                request.setAttribute("anadidas", afarm_dao.listar_anadidas());
                RequestDispatcher viewAdd = request.getRequestDispatcher("/Administracion/historial_anadidas.jsp");
                viewAdd.forward(request, response);
                break;
            case "block":
                request.setAttribute("bloqueadas", bfarm_dao.listar_bloqueadas());
                RequestDispatcher viewBlock = request.getRequestDispatcher("/Administracion/historial_bloqueadas.jsp");
                viewBlock.forward(request, response);
                break;
            case "unlock":
                request.setAttribute("desbloqueadas", dfarm_dao.listar_desbloqueadas());
                RequestDispatcher viewUnlock = request.getRequestDispatcher("/Administracion/historial_desbloqueadas.jsp");
                viewUnlock.forward(request, response);
                break;
            default:
                RequestDispatcher view = request.getRequestDispatcher("/Administracion/historial_admin.jsp");
                view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
