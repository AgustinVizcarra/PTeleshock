package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.DetProdDao;
import pe.edu.pucp.pteleshock.Dao.RegistrarProDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "Farm_Editar_Inf_ProductoServlet", value = "/Farm_Editar_Inf_Producto")
@MultipartConfig
public class Farm_Editar_Inf_ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");
        int idF = (Integer) session.getAttribute("idFarmacia");

        if (farmacia != null) {

        response.setContentType("text/html");

        String prod = request.getParameter("prod");

        String msg = request.getParameter("msg") !=null ? request.getParameter("msg"): "reg";

        DetProdDao detProdDao= new DetProdDao();


        switch (msg){
            case "ok":
                request.setAttribute("val","ok");
                request.setAttribute("idp",prod);
                request.setAttribute("listadetallesP",detProdDao.listadetallesP(idF,prod));
                RequestDispatcher view = request.getRequestDispatcher("/Farmacia/editar_info_producto.jsp");
                view.forward(request,response);
                break;
            case "error":
                request.setAttribute("val","error");
                request.setAttribute("idp",prod);
                request.setAttribute("listadetallesP",detProdDao.listadetallesP(idF,prod));
                RequestDispatcher view2 = request.getRequestDispatcher("/Farmacia/editar_info_producto.jsp");
                view2.forward(request,response);
                break;
            case "reg":

                request.setAttribute("idp",prod);
                request.setAttribute("listadetallesP",detProdDao.listadetallesP(idF,prod));
                RequestDispatcher view3 = request.getRequestDispatcher("/Farmacia/editar_info_producto.jsp");
                view3.forward(request,response);

        }


        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        RegistrarProDao registrarProDao = new RegistrarProDao();


        String stockStr= request.getParameter("stock").strip() != "" ? request.getParameter("stock") : "a";
        String precioUnitarioStr= request.getParameter("precioUnitario").strip() != "" ? request.getParameter("precioUnitario") : "a";
        String descripcion=request.getParameter("descripcion").strip() != "" ? request.getParameter("descripcion") : "a";
        String recetamedica=request.getParameter("recetamedica").strip() != "" ? request.getParameter("recetamedica") : "a";

        String idprodstr = request.getParameter("prod").strip();


        Part part=request.getPart("foto");
        InputStream inputStream=part.getInputStream();


        if( stockStr.equals("a") || precioUnitarioStr.equals("a") || descripcion.equals("a") || recetamedica.equals("a")){
            response.sendRedirect(request.getContextPath() + "/Farm_Editar_Inf_Producto?msg=error&prod="+idprodstr);

        }else{
            int idprod= Integer.parseInt(idprodstr);

            int stock=Integer.parseInt(stockStr);
            double precioUnitario=Double.parseDouble(precioUnitarioStr);

            int idF = (Integer) session.getAttribute("idFarmacia");

            registrarProDao.actualizarProducto(idF,idprod,stock,precioUnitario,descripcion,recetamedica,inputStream);

            //hace una nueva solicitud
//            response.sendRedirect(request.getContextPath()+"/Farm_Detalles_Producto?prod="+idprod);
            response.sendRedirect(request.getContextPath() + "/Farm_Editar_Inf_Producto?msg=ok&prod="+idprodstr);


        }


    }
}
