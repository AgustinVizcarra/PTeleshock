package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.BolsaCompraDao;
import pe.edu.pucp.pteleshock.Dao.DetProdDao;
import pe.edu.pucp.pteleshock.Dao.RegistrarProDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "ImgRecetaServlet", value = "/ImgRecetaServlet")
@MultipartConfig
public class ImgRecetaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
//        int idF = (Integer) session.getAttribute("idFarmacia");
        String idfarmstr = request.getParameter("idfarm");
        int idF= Integer.parseInt(idfarmstr);
        DetProdDao detprodao = new DetProdDao();
        String prod = request.getParameter("prod");
        String idpedidostr=request.getParameter("idped");
        int idpedido= Integer.parseInt(idpedidostr);
        detprodao.listarImgReceta(idF,prod,idpedido,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        BolsaCompraDao bolsaCompraDao = new BolsaCompraDao();

        String idprodstr = request.getParameter("idProd").strip();
        String idpedstr = request.getParameter("idP");
        String idfarmstr = request.getParameter("idF");
        int idprod= Integer.parseInt(idprodstr);
        int idped= Integer.parseInt(idpedstr);
        int idF = Integer.parseInt(idfarmstr);
        Part part=request.getPart("fotoReceta");
        InputStream inputStream=part.getInputStream();

        bolsaCompraDao.actualizarFotoreceta(idF,idped,idprod,inputStream);
        response.sendRedirect(request.getContextPath() + "/Client_Bolsa_Compra");


    }
}