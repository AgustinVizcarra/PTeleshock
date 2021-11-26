package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.PerfilFarmDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Farm_IndexServlet", value = "/Farm_Index")
public class Farm_IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PerfilFarmDao pfdao = new PerfilFarmDao();
        request.setAttribute("perfilfarm",pfdao.perfilFarmacia());
        RequestDispatcher view = request.getRequestDispatcher("/Farmacia/index_farmacia.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
