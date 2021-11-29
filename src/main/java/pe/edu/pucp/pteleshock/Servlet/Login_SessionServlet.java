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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session_login = request.getSession();
        session_login.removeAttribute("logid");
        String mensaje = request.getParameter("mensaje") == null ? "" : request.getParameter("mensaje");
        String mail = request.getParameter("correo") == null ? "" : request.getParameter("correo");
        String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");

        request.setAttribute("pwd", pwd);
        request.setAttribute("mail", mail);
        request.setAttribute("mensajealerta", mensaje);



        RequestDispatcher view = request.getRequestDispatcher("/Login/login.jsp");
        view.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action") != null ? request.getParameter("action") : "";

        UsuarioDao usuarioDao = new UsuarioDao();

        switch (action) {
            case "login":
                String correo = request.getParameter("correo") != null ? request.getParameter("correo") : "";
                String pass = request.getParameter("pass") != null ? request.getParameter("pass") : "";

                BUsuario usuario = usuarioDao.validarUsuarioPassword(correo, pass);
                String mensaje = "";

                if (usuario != null) {
                    HttpSession session = request.getSession();

                    int rol = usuario.getIdRol();
                    switch (rol) {
                        case 1:
                            session.setAttribute("clienteSession", usuario);
                            response.sendRedirect(request.getContextPath() + "/Client_Farmacias");
                            break;
                        case 2:
                            session.setAttribute("adminSession", usuario);
                            response.sendRedirect(request.getContextPath() + "/Admin_Index");
                            break;
                        case 3:
                            //debemos verificar que la farmacia no se encuentra bloqueada
                            if (usuarioDao.farmaciaBloqueada(usuario.getIdUsuario())) {
                                mensaje = "La farmacia se encuentra bloqueada, por favor ponerse en contacto con el Administrador";
                                response.sendRedirect(request.getContextPath() + "/Login?mensaje=" + mensaje + "&correo=" + correo + "&pwd=" + pass);
                            } else {
                                session.setAttribute("farmaciaSession", usuario);
                                response.sendRedirect(request.getContextPath() + "/Farm_Index");
                            }
                            break;
                        default:
                            System.out.println("ocurrio un error en el switch del login");
                    }

                    System.out.println("tracer 1");
                } else {
                    mensaje = "Contraseña o Dirección de correo invalidos";
                    response.sendRedirect(request.getContextPath() + "/Login?mensaje=" + mensaje + "&correo=" + correo + "&pwd=" + pass);
                    System.out.println("tracer 2");
                }
                break;
            case "logout":

                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/Login");

                break;


        }


    }
}
