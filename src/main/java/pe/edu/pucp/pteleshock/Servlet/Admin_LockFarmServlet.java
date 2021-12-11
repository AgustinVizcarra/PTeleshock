package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BFarmacia;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.FarmaciaDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Admin_BloqFarmServlet", value = "/Admin_BloqFarm")
public class Admin_LockFarmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        BUsuario admin = (BUsuario) session.getAttribute("adminSession");
        if (admin != null) {
            String mensaje = request.getParameter("mensaje") != null ? request.getParameter("mensaje") : "";
            String ruc = request.getParameter("ruc") != null ? request.getParameter("ruc") : "";
            String razon = request.getParameter("razon") != null ? request.getParameter("razon") : "";
            request.setAttribute("mensaje", mensaje);
            request.setAttribute("rucListado", ruc);
            request.setAttribute("razon", razon);
            RequestDispatcher view = request.getRequestDispatcher("/Administracion/bloquear_farmacia.jsp");
            view.forward(request, response);
        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String razon = request.getParameter("razon") != null ? request.getParameter("razon") : "";
        String ruc = request.getParameter("ruc") != null ? request.getParameter("ruc") : "";
        String admin = request.getParameter("admin") != null ? request.getParameter("admin") : "";

        System.out.println(ruc);

        FarmaciaDao farmaciaDao = new FarmaciaDao();
        BFarmacia farmacia = farmaciaDao.getFarmacia(ruc);

        String mensaje = "Se ha bloqueado exitosamente";

        boolean campos_nulos = false;

        if (request.getParameter("razon").isEmpty() || request.getParameter("ruc").isEmpty()) {
            campos_nulos = true;
            mensaje = "Usted envio campos nulos, por favor intente de nuevo";
        }
        if (!campos_nulos) {
            if (farmaciaDao.existeFarmacia(ruc)) {
//            System.out.println("Farmacia existente");

                if (farmacia.getEstado().equals("bloqueado")) {
                    mensaje = "No se puede bloquear, ya se encuentra bloqueada!!!!";

                } else {
                    if (farmacia.getPedPendientes() == 0) {
                        farmaciaDao.actualizarFarmaciaBloqueada(farmacia, razon, admin);
                    } else {
                        mensaje = "No se puede bloquear, tiene pedidos pendientes!!!!";
                    }
                }

            } else {
                mensaje = "El RUC introducido no existe o se ha introducido un nombre incorrecto";
            }
        }
        //System.out.println(mensaje);
        response.sendRedirect(request.getContextPath() + "/Admin_BloqFarm?mensaje=" + mensaje + "&ruc=" + ruc + "&razon" + razon);
    }
}

