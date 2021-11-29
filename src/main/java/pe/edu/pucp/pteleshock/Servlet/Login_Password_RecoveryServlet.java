package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.PaswordDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login_Password_RecoveryServlet", value = "/Login_Password_Recovery")
public class Login_Password_RecoveryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String idUStr = request.getParameter("idusuario")!=null? request.getParameter("idusuario"):"0";
        System.out.println(idUStr);
        request.setAttribute("idusuario",idUStr);
        RequestDispatcher view = request.getRequestDispatcher("/Login/recuperacion_contraseña.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id_user"));
        System.out.println(id);
        String pwd = request.getParameter("pwd1");
        PaswordDao paswordDao = new PaswordDao();
        System.out.println(paswordDao);
        //cambio de contraseña
        paswordDao.cambiarContra(id,pwd);
        paswordDao.confirmacionCambioContra(id);
        System.out.println("Cambio de contraseña exitoso");
        response.sendRedirect(request.getContextPath()+"/Login_Password_Recovery#popup1");
    }
}
