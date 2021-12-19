package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BLbloqueadas;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.ValidacionUnl_Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Admin_UnlockFarmServlet", value = "/Admin_UnlockFarm")
public class Admin_UnlockFarmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        ValidacionUnl_Dao ufarmDao = new ValidacionUnl_Dao();
        String mensaje = request.getParameter("mensaje")==null?"":request.getParameter("mensaje");
        String idfarmacia = request.getParameter("idfarmacia")==null?"":request.getParameter("idfarmacia");
        ArrayList<BLbloqueadas> farmaciabloqueadas = ufarmDao.listarBloqueada();
        session.setAttribute("farmaciasBloqueadas",farmaciabloqueadas);
        request.setAttribute("mensaje",mensaje);
        request.setAttribute("idfarm",idfarmacia);
        RequestDispatcher view = request.getRequestDispatcher("/Administracion/desbloquear_farmacia.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idFarmaciastr = request.getParameter("farmaciaId")==null?"":request.getParameter("farmaciaId");
        String razon = request.getParameter("razon").strip()==null?"":request.getParameter("razon").strip();
        String admin = request.getParameter("admin");
        response.setCharacterEncoding("UTF-8");
        //verificacion de campos nulos
        String mensaje = "Desbloqueo exitoso";
        if(!idFarmaciastr.equalsIgnoreCase("")&&!razon.equalsIgnoreCase("")){
            //realizamos el parseo
            int idfarm = Integer.parseInt(idFarmaciastr);
            //se actualiza la farmacia
            ValidacionUnl_Dao ufarmDao = new ValidacionUnl_Dao();
            ufarmDao.cambiarEstado(idfarm);
            ufarmDao.registrohistorial(idfarm,admin,razon);
            System.out.println("Registro exitoso");
        }else{
            mensaje="Usted ingreso campos nulos, por favor intente de nuevo";
        }
        response.sendRedirect(request.getContextPath()+"/Admin_UnlockFarm?mensaje="+mensaje);
    }
}
