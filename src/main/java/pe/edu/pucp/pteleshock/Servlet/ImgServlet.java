package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.DetProdDao;
import pe.edu.pucp.pteleshock.Dao.RegistrarProDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "ImgServlet", value = "/ImgServlet")
@MultipartConfig
public class ImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        int idF = (Integer) session.getAttribute("idFarmacia");
        String prod = request.getParameter("prod");
        DetProdDao detprodao = new DetProdDao();
        detprodao.listarImg(idF,prod,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        RegistrarProDao registrarProDao = new RegistrarProDao();

        String idprodstr = request.getParameter("prod").strip();

        int idprod= Integer.parseInt(idprodstr);
        int idF = (Integer) session.getAttribute("idFarmacia");
        Part part=request.getPart("foto");
        InputStream inputStream=part.getInputStream();

        registrarProDao.actualizarFoto(idprod,idF,inputStream);
        response.sendRedirect(request.getContextPath() + "/Farm_Editar_Inf_Producto?prod="+idprodstr);
    }
}