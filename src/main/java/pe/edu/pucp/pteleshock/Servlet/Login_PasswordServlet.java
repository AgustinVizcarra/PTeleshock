package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.ValidacionAdd_Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login_PasswordServlet", value = "/Login_Password")
public class Login_PasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String mensaje = request.getParameter("mensaje")!=null?request.getParameter("mensaje"):"";
        String correo = request.getParameter("email")!=null?request.getParameter("email"):"";
        request.setAttribute("mensaje",mensaje);
        request.setAttribute("correo",correo);
        RequestDispatcher view = request.getRequestDispatcher("/Login/contraseña.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String correo = request.getParameter("email")!=null?request.getParameter("email"):"";
        System.out.println("Correo: "+correo);
        ValidacionAdd_Dao validacionAdd_dao = new ValidacionAdd_Dao();
        String mensaje = "";
        //System.out.println(correo);
        int idusuario = validacionAdd_dao.getIdusuario(correo);
        //System.out.println(idusuario);
        if(!correo.equalsIgnoreCase("")){
            if(validacionAdd_dao.email_unico(correo)){
                //response.sendRedirect(request.getContextPath()+"/Login_Password_Recovery?idusuario="+idusuario);
                // se crea una sesión:
                HttpSession session_login = request.getSession();
                String idStr = String.valueOf(idusuario);
                session_login.setAttribute("logid",idStr);
                response.sendRedirect(request.getContextPath()+"/Login_Password_Recovery");
            }else {
                mensaje = "No se encuentra la ruta de correo especificada";
                response.sendRedirect(request.getContextPath()+"/Login_Password?mensaje="+mensaje);
            }
        }else{
            mensaje = "Digito un campo vacio";
            response.sendRedirect(request.getContextPath()+"/Login_Password?mensaje="+mensaje);
        }
    }
}
