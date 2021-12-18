package pe.edu.pucp.pteleshock.Servlet;

import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.PxFarDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Farm_Vista_ProductosServlet", value = "/Farm_Vista_ProductosServlet")
public class Farm_Vista_ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html");

        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");

        if (farmacia != null) {
            int idF = (Integer) session.getAttribute("idFarmacia");
            String pag = request.getParameter("pag") !=null ? request.getParameter("pag"): "1";

            PxFarDao pxFarDao=new PxFarDao();

            int cant=pxFarDao.cantidadProductosF(idF);
            String cantStr=String.valueOf(cant);

            boolean isNumeric =  false;

            if(!(pag.equals(""))){
                isNumeric =  pag.matches("[+-]?\\d*(\\.\\d+)?");
            }

            if(isNumeric){

                int resto = cant%12==0? 0:1;
                int pagInt = Integer.parseInt(pag);
                if(pagInt <= (Math.floor(cant/12)+resto)){
                    request.setAttribute("listaxFarmacia",pxFarDao.listarProductosF(idF,pag));
                    request.setAttribute("cantProd",cantStr);
                    RequestDispatcher view = request.getRequestDispatcher("/Farmacia/visualizar_productos.jsp");
                    view.forward(request,response);
                }else{
                    request.setAttribute("listaxFarmacia",pxFarDao.listarProductosF(idF,"1"));
                    request.setAttribute("cantProd",cantStr);
                    RequestDispatcher view = request.getRequestDispatcher("/Farmacia/visualizar_productos.jsp");
                    view.forward(request,response);
                }



            }else{

                request.setAttribute("listaxFarmacia",pxFarDao.listarProductosF(idF,"1"));
                request.setAttribute("cantProd",cantStr);
                RequestDispatcher view = request.getRequestDispatcher("/Farmacia/visualizar_productos.jsp");
                view.forward(request,response);
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
        String texto = request.getParameter("textoBuscar");
        String pag = request.getParameter("pag") !=null ? request.getParameter("pag"): "1";

        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");

        if (farmacia != null) {
            int idF = (Integer) session.getAttribute("idFarmacia");
            PxFarDao pxFarDao=new PxFarDao();
            if (texto == null) {
                response.sendRedirect(request.getContextPath() + "/Farm_Vista_ProductosServlet");
            } else {
                int cant=pxFarDao.cantidadProductosBuscados(idF,texto);
                String cantStr=String.valueOf(cant);
                request.setAttribute("cantProd",cantStr);
                request.setAttribute("textbuscar",texto);

                boolean isNumeric =  false;

                if(!(pag.equals(""))){
                    isNumeric =  pag.matches("[+-]?\\d*(\\.\\d+)?");
                }

                if(isNumeric){
                    request.setAttribute("listaxFarmacia", pxFarDao.buscarProductoPorNombre(idF,pag,texto));
                    RequestDispatcher view = request.getRequestDispatcher("/Farmacia/visualizar_productosFiltrados.jsp");
                    view.forward(request, response);

                }else{
                    request.setAttribute("listaxFarmacia", pxFarDao.buscarProductoPorNombre(idF,"1",texto));
                    RequestDispatcher view = request.getRequestDispatcher("/Farmacia/visualizar_productosFiltrados.jsp");
                    view.forward(request, response);
                }


            }

        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }



    }


}
