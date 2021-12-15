package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Dao.DetProdDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ImgRecetaServlet", value = "/ImgRecetaServlet")
@MultipartConfig
public class ImgRecetaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        int idF = (Integer) session.getAttribute("idFarmacia");
        DetProdDao detprodao = new DetProdDao();
        String prod = request.getParameter("prod");
        String idpedidostr=request.getParameter("idped");
        int idpedido= Integer.parseInt(idpedidostr);
        detprodao.listarImgReceta(idF,prod,idpedido,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}