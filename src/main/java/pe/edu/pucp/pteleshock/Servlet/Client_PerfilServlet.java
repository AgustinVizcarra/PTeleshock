package pe.edu.pucp.pteleshock.Servlet;
import pe.edu.pucp.pteleshock.Beans.BCliente;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Client_PerfilServlet", value = "/Client_Perfil")
public class Client_PerfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");
        if(cliente != null) {
            PerfilClientDao perfilClientDao = new PerfilClientDao();
            ListaDistritosDao listaDistritosDao = new ListaDistritosDao();
            String action = request.getParameter("action") == null ? "mostrar" : request.getParameter("action");
            switch (action) {
                case "mostrar":
                    //Implementar cliente dinamico -> Sesiones
                    request.setAttribute("cliente", perfilClientDao.clientePerfil(cliente.getIdUsuario()));
                    RequestDispatcher view = request.getRequestDispatcher("/Cliente/mi_perfil.jsp");
                    view.forward(request, response);
                    break;
                case "editar":
                    String valor = request.getParameter("valor") == null ? "" : request.getParameter("valor");
                    //Mensaje
                    String er1 = request.getParameter("er") == null ? "" : request.getParameter("er");
                    //Editar Perfil

                    //Se recibe idC de boton "editar"
                    String idClienteStr = request.getParameter("idC") != null ? request.getParameter("idC") : "";

                    int idC = 0;
                    try {
                        idC = Integer.parseInt(idClienteStr);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    request.setAttribute("listaDistritos", listaDistritosDao.listarTotalDistritos());

                    BCliente bCliente = perfilClientDao.clientePerfil(idC);
                    //System.out.println("nombre Part"+bCliente.getNombre());

                    if (bCliente == null) {
                        response.sendRedirect(request.getContextPath() + "/Client_Perfil");
                    } else {
                        if (er1.equals("1")) {
                            request.setAttribute("er", "¡Ha ingresado datos incorrectos!");
                        }
                        if (valor.equals("edit")) {
                            request.setAttribute("valor", "edit");
                        }

                        request.setAttribute("cliente", perfilClientDao.clientePerfil(idC));
                        view = request.getRequestDispatcher("Cliente/edicion_de_perfil.jsp");
                        view.forward(request, response);
                    }
                    break;
                case "elimLogico":
                    String idClienteStr1 = request.getParameter("idC") != null ? request.getParameter("idC") : "";
                    if (perfilClientDao.validacionElimCliente(Integer.parseInt(idClienteStr1))){
                        perfilClientDao.elimLogicoCliente(Integer.parseInt(idClienteStr1));
                        response.sendRedirect(request.getContextPath() + "/Client_Perfil#popup2");
                    }else{
                        response.sendRedirect(request.getContextPath() + "/Client_Perfil#popup3");
                    }
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/Client_Perfil");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ?"actualizar" : request.getParameter("action");
        System.out.println(action);
        PerfilClientDao perfilClientDao = new PerfilClientDao();
        RegistrarClienteDao registrarClienteDao = new RegistrarClienteDao();
        UsuarioDao usuarioDao = new UsuarioDao();

        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");

        switch (action){

            case "actualizar":
                String idClienteStr = request.getParameter("idC") != null ? request.getParameter("idC") : "";
                String nombreStr = request.getParameter("nombreC").strip() != "" ? request.getParameter("nombreC").strip() : "a";
                String apellidoStr = request.getParameter("apellidoC").strip() != "" ? request.getParameter("apellidoC").strip() : "a";
                String idDisStr = request.getParameter("idDis") != null ? request.getParameter("idDis"): "";

                int idCliente = Integer.parseInt(idClienteStr);
                int idDis = Integer.parseInt(idDisStr);
                if(nombreStr.equals("a") || apellidoStr.equals("a")){
                    response.sendRedirect(request.getContextPath() + "/Client_Perfil?action=editar&er=1&idC=" + idCliente);
                }else{
                    perfilClientDao.editarCliente(idCliente,nombreStr,apellidoStr,idDis);
                    session.setAttribute("clienteSession",(BUsuario) usuarioDao.obtenerUsuario(idCliente));
                    response.sendRedirect(request.getContextPath() + "/Client_Perfil?valor=edit");
                }
                break;
        }
    }
}
