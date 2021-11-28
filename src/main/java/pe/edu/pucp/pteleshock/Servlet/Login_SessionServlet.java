package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuarioCliente;
import pe.edu.pucp.pteleshock.Dao.UsuarioClienteDao;

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
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action")!=null?request.getParameter("action"):"";

        UsuarioClienteDao clienteDao = new UsuarioClienteDao();

        switch (action){
            case "login":
                String correo = request.getParameter("correo")!=null?request.getParameter("correo"):"";
                String pass = request.getParameter("pass")!=null?request.getParameter("pass"):"";

                BUsuarioCliente cliente = clienteDao.validarClientePassword(correo,pass);
                if (cliente!= null){
                    HttpSession session = request.getSession();
                    session.setAttribute("clienteSession",cliente);

                    response.sendRedirect(request.getContextPath() +"/Client_Farmacias?idD="+cliente.getDistritos().getIdDistrito());

                    System.out.println("tracer 1");
                }else {
                    response.sendRedirect(request.getContextPath() + "/Login?error");
                    System.out.println("tracer 2");
                }

                break;


        }

    }
}
