package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BPedidoEstado;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.BolsaCompraDao;
import pe.edu.pucp.pteleshock.Dao.UsuarioDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/Login")
public class Login_SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session_login = request.getSession();
        session_login.removeAttribute("logid");
        //String mensaje = request.getParameter("mensaje") == null ? "" : request.getParameter("mensaje");
        //String mail = request.getParameter("correo") == null ? "" : request.getParameter("correo");
        //String pwd = request.getParameter("pwd") == null ? "" : request.getParameter("pwd");
        //if(mensaje.equals("a")){
          //  mensaje = "Se ha ingresado una contraseña o dirección de correo invalidos.";
        //}
        //request.setAttribute("pwd", pwd);
        //request.setAttribute("mail", mail);
        //request.setAttribute("mensajealerta", mensaje);
        RequestDispatcher view = request.getRequestDispatcher("/Login/login.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";

        UsuarioDao usuarioDao = new UsuarioDao();

        switch (action) {

            case "login":
                String correo = request.getParameter("correo") != null ? request.getParameter("correo") : "";
                String pass = request.getParameter("pass") != null ? request.getParameter("pass") : "";

                //BUsuario usuario = usuarioDao.validarUsuarioPassword(correo, pass);
                BUsuario usuario = usuarioDao.validarUsuarioPasswordHashed(correo,pass);
                String mensaje = "";

                if (usuario != null) {
                    HttpSession session = request.getSession();

                    int rol = usuario.getIdRol();
                    switch (rol) {
                        case 1:
                            session.setAttribute("clienteSession", usuario);
                            session.setAttribute("bolsita",new ArrayList<BPedidoEstado>());
                            int codigoVenta=0;
                            session.setAttribute("codVenta",codigoVenta);
                            HashMap<Integer, Integer> map= new HashMap<>();
                            map.put(0,0);
                            session.setAttribute("maps",map);
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
                    mensaje = "a";
                    HttpSession session_e = request.getSession();
                    session_e.setAttribute("mensajealerta",mensaje);
                    session_e.setAttribute("mail",correo);
                    session_e.setAttribute("pwd",pass);
                    response.sendRedirect(request.getContextPath() + "/Login");
                }
                break;
            case "logout":
                HttpSession session = request.getSession();
                if(session.getAttribute("clienteSession")!=null) {//corrección by Agustin
                    ArrayList<BPedidoEstado> listBolsa1 = (ArrayList<BPedidoEstado>) session.getAttribute("bolsita");
                    BolsaCompraDao bolsaCompraDao = new BolsaCompraDao();
                    if (!listBolsa1.isEmpty()) {
                        for (int i = 0; i < listBolsa1.size(); i++) {
                            int idPed = listBolsa1.get(i).getPedido().getIdPedido();
                            int idProd = listBolsa1.get(i).getIdProducto();
                            int idFarm = listBolsa1.get(i).getPedido().getIdFarmacia();
                            bolsaCompraDao.eliminarCarrito(idProd, idPed, idFarm);
                        }
                    }
                }
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/Login");
                break;
        }

    }
}
