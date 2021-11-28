package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.UsuarioDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/Login")
public class Login_SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher view = request.getRequestDispatcher("/Login/login.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") != null ? request.getParameter("action") : "";

        UsuarioDao usuarioDao = new UsuarioDao();

        switch (action) {
            case "login":
                String correo = request.getParameter("correo") != null ? request.getParameter("correo") : "";
                String pass = request.getParameter("pass") != null ? request.getParameter("pass") : "";

                BUsuario usuario = usuarioDao.validarUsuarioPassword(correo, pass);
                if (usuario != null) {
                    HttpSession session = request.getSession();

                    int rol = usuario.getRol().getIdRol();
                    System.out.println(usuario.getRol().getIdRol());

                    switch (rol) {
                        case 1:
                            session.setAttribute("clienteSession", usuario);
                            response.sendRedirect(request.getContextPath() + "/Client_Farmacias?idD=" + usuario.getDistritos().getIdDistrito());
                            break;
                        case 2:
                            session.setAttribute("adminSession", usuario);
                            //agregar
                            break;
                        case 3:
                            session.setAttribute("farmaciaSession", usuario);
                            response.sendRedirect(request.getContextPath() + "/Farm_Index?idF="+ usuario.getIdUsuario());
                            System.out.println(usuario.getIdUsuario());
                            System.out.println("entro a farmacia");
                            break;
                    }

                    System.out.println("tracer 1");
                } else {
                    response.sendRedirect(request.getContextPath() + "/Login?error");
                    System.out.println("tracer 2");
                }

                break;


        }

    }
}
