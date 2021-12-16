package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.PaswordDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login_Password_RecoveryServlet", value = "/Login_Password_Recovery")
public class Login_Password_RecoveryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //String correo = request.getParameter("correo")==null?"":request.getParameter("correo");
        RequestDispatcher view = request.getRequestDispatcher("/Login/recuperacion_contraseña.jsp");
        //session_login.removeAttribute("logid");
        view.forward(request, response);
        //System.out.println("Paso por acá");
        HttpSession session = request.getSession();
        session.setAttribute("validacion",0);
        PaswordDao paswordDao = new PaswordDao();
        paswordDao.invalidarToken((String) session.getAttribute("correo"));
        System.out.println(paswordDao.verificarToken((String) session.getAttribute("correo"))?"Token valido":"Token invalido");
        //System.out.println("Se cambio el valor");
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
        System.out.println("Luego paso por aquí");
        response.sendRedirect(request.getContextPath()+"/Login_Password_Recovery#popup1");
    }
}
