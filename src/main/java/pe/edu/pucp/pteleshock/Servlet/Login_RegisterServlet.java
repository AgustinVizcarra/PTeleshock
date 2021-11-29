package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.ListaDistritosDao;
import pe.edu.pucp.pteleshock.Dao.PerfilClientDao;
import pe.edu.pucp.pteleshock.Dao.RegistrarClienteDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login_RegisterServlet", value = "/Login_Register")
public class Login_RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "anadir" : request.getParameter("action");
        RequestDispatcher view;

        ListaDistritosDao listaDistritosDao = new ListaDistritosDao();
        RegistrarClienteDao registrarClienteDao = new RegistrarClienteDao();

        switch (action) {
            case "anadir":
                String er = request.getParameter("er") == null ? "" : request.getParameter("er");

                if (er.equals("1")) {
                    request.setAttribute("er", "¡Ha ingresado datos incorrectos!");
                }
                if (er.equals("2")) {
                    request.setAttribute("er", "¡Ha ingresado un correo o dni ya registrado! Inténtelo de nuevo.");
                }
                if (er.equals("3")) {
                    request.setAttribute("er", "Las contraseñas no coinciden. Verificar que sean las mismas.");
                }

                request.setAttribute("listaDistritos", listaDistritosDao.listarTotalDistritos());
                view = request.getRequestDispatcher("/Login/registro.jsp");
                view.forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "actualizar" : request.getParameter("action");
        RegistrarClienteDao registrarClienteDao = new RegistrarClienteDao();

        switch (action) {
            case "crear":
                String idDisStr = request.getParameter("idDis") != null ? request.getParameter("idDis") : "";
                String nombre = request.getParameter("nombreC").strip() != "" ? request.getParameter("nombreC") : "a";
                String apellido = request.getParameter("apellidoC").strip() != "" ? request.getParameter("apellidoC") : "a";
                String correo = request.getParameter("correo").strip() != "" ? request.getParameter("correo") : "a";
                String dni = request.getParameter("dni").strip() != ""? request.getParameter("dni") : "a";
                String contrasenia = request.getParameter("contrasenia").strip() != "" ? request.getParameter("contrasenia") : "a";
                String contrasenia2 = request.getParameter("contrasenia2").strip() != "" ? request.getParameter("contrasenia2") : "a";
                int idDistrito = Integer.parseInt(idDisStr);

                System.out.println(contrasenia);
                System.out.println(dni);
                System.out.println(correo);



                if(nombre.equals("a") || apellido.equals("a") || correo.equals("a") || dni.equals("a") || contrasenia.equals("a") || contrasenia2.equals("a")){
                    response.sendRedirect(request.getContextPath() + "/Login_Register?action=anadir&er=1");

                }else{
                    if(registrarClienteDao.correoExistente(correo) || registrarClienteDao.dniExistente(dni)){
                        response.sendRedirect(request.getContextPath() + "/Login_Register?action=anadir&er=2");
                    }else{
                        if(contrasenia.equals(contrasenia2)){
                            registrarClienteDao.añadirCliente(idDistrito, nombre, apellido, correo, dni, contrasenia);
                            registrarClienteDao.enviarCorreoRegistro(correo,nombre);
                            response.sendRedirect(request.getContextPath() + "/Login");
                        }else{
                            response.sendRedirect(request.getContextPath() + "/Login_Register?action=anadir&er=3");
                        }
                    }
                }
                break;

        }
    }

}
