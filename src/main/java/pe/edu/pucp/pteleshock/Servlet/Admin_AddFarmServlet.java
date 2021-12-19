package pe.edu.pucp.pteleshock.Servlet;


import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.ListadoFarmaciasDao;
import pe.edu.pucp.pteleshock.Dao.ValidacionAdd_Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Admin_AddFarmServlet", value = "/Admin_AddFarm")
public class Admin_AddFarmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensaje = request.getParameter("mensaje") != null ? request.getParameter("mensaje") : "";
        String nombre = request.getParameter("nombre") != "" ? request.getParameter("nombre") : "";
        String ruc = request.getParameter("ruc") != "" ? request.getParameter("ruc") : "";
        String correo = request.getParameter("correo") != "" ? request.getParameter("correo") : "";
        String direccion = request.getParameter("direccion") != "" ? request.getParameter("direccion") : "";
        String id_distrito = request.getParameter("distrito") != "0" ? request.getParameter("distrito") : "0";
        request.setAttribute("distrito", id_distrito);
        request.setAttribute("nombre", nombre);
        request.setAttribute("ruc", ruc);
        request.setAttribute("correo", correo);
        request.setAttribute("direccion", direccion);
        request.setAttribute("mensaje", mensaje);
        response.setContentType("text/html");
        ListadoFarmaciasDao listadoFarmaciasDao = new ListadoFarmaciasDao();
        request.setAttribute("distritos", listadoFarmaciasDao.listar_distritos());
        RequestDispatcher view = request.getRequestDispatcher("/Administracion/añadir_farmacia.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ya se confirmaron que los valores correspondientes llegan
        //Verificacion de creacion de un
        request.setCharacterEncoding("UTF-8");
        boolean todo_claro = true;
        boolean campos_nulos = false;
        ValidacionAdd_Dao validacionAdd_dao = new ValidacionAdd_Dao();
        String mensaje = "Se logro registrar de manera correcta";
        if ((request.getParameter("nombre_farmacia").strip().isEmpty() || request.getParameter("ruc_farmacia").strip().isEmpty() || request.getParameter("distrito_farmacia") == null ||
                request.getParameter("correo_farmacia").strip().isEmpty())) {
            campos_nulos = true;
            todo_claro = false;
            mensaje = "Usted envio campos nulos, por favor intente de nuevo";
        }
        System.out.println("campos nulos: " + campos_nulos);
        if (!campos_nulos) {
            //confirmar que el ruc es unico
            boolean ruc_repetida = validacionAdd_dao.ruc_unico((String) request.getParameter("ruc_farmacia"));
            if (ruc_repetida) {
                mensaje = "Usted envio un RUC que ya se encuentra registrado";
                todo_claro = false;
            }
            //confirmar que el nombre es correcto y no son puros numeros
            boolean nombre_incorrecto = validacionAdd_dao.nombre_correcto((String) request.getParameter("nombre_farmacia"));
            if (nombre_incorrecto) {
                mensaje = "Usted digito un nombre de farmacia que solo contiene numeros";
                todo_claro = false;
            }
            //confirma si la dirección de correo no se encuentra utilizada
            boolean correo_repetido = validacionAdd_dao.email_unico((String) request.getParameter("correo_farmacia"));
            if (correo_repetido) {
                mensaje = "Usted usa un correo que ya se encuentra utilizado";
                todo_claro = false;
            }
        }
        String nombre_farmacia = "";
        String ruc_farmacia = "";
        String correo_farmacia = "";
        String direccion_farmacia = "";
        int distrito_farmacia;
        nombre_farmacia = request.getParameter("nombre_farmacia").strip();
        ruc_farmacia = request.getParameter("ruc_farmacia").strip();
        correo_farmacia = request.getParameter("correo_farmacia").strip();
        direccion_farmacia = request.getParameter("direccion_farmacia").strip();
        if (request.getParameter("distrito_farmacia") != null) {
            distrito_farmacia = Integer.parseInt(request.getParameter("distrito_farmacia"));
        } else {
            distrito_farmacia = 0;
        }
        if (todo_claro) {
            String admin = request.getParameter("admin");
            validacionAdd_dao.insertar_farmacia_usuario(nombre_farmacia, ruc_farmacia, distrito_farmacia, correo_farmacia);
            validacionAdd_dao.insertar_farmacia_farmacia(direccion_farmacia, ruc_farmacia);
            validacionAdd_dao.insertar_registro_historial(admin, ruc_farmacia);
            //una vez que ya se tiene correo
            validacionAdd_dao.enviar_correo(correo_farmacia, nombre_farmacia);
            System.out.println("Registro exitoso");
        } else {
            System.out.println("Hubo error");
        }
        //request.getSession().setAttribute("mensaje",mensaje);
        response.setCharacterEncoding("UTF-8");
        response.sendRedirect(request.getContextPath() + "/Admin_AddFarm?mensaje=" + mensaje + "&nombre=" + nombre_farmacia + "&ruc=" + ruc_farmacia + "&correo=" + correo_farmacia + "&direccion=" + direccion_farmacia + "&distrito=" + distrito_farmacia);
    }
}
