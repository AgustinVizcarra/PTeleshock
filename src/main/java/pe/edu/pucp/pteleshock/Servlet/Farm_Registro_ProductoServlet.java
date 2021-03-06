package pe.edu.pucp.pteleshock.Servlet;



import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.RegistrarProDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@WebServlet(name = "Farm_Registro_ProductoServlet", value = "/Farm_Registro_Producto")
@MultipartConfig
public class Farm_Registro_ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");

        if (farmacia != null) {


        response.setContentType("text/html");

        String msg = request.getParameter("msg") !=null ? request.getParameter("msg"): "reg";
        String nom= request.getParameter("nom") !="" ? request.getParameter("nom"): "";
        String des= request.getParameter("des") !="" ? request.getParameter("des"): "";
        String rec= request.getParameter("rec") !=null ? request.getParameter("rec"): "";

        String stock= request.getParameter("sto") !="1" ? request.getParameter("sto"): "";
        String precioU= request.getParameter("prec") !="1" ? request.getParameter("prec"): "";

        ArrayList<String> datosProd = new ArrayList<>();

        datosProd.add(nom);
        datosProd.add(des);
        datosProd.add(rec);

        datosProd.add(stock);
        datosProd.add(precioU);


        switch (msg){
            case "ok":
                request.setAttribute("val","ok"); //se borra
                request.setAttribute("listDatos",datosProd);
                RequestDispatcher view = request.getRequestDispatcher("/Farmacia/registro_producto.jsp");
                view.forward(request,response);
                break;
            case "error":
                request.setAttribute("val","error");
                request.setAttribute("listDatos",datosProd);
                RequestDispatcher view2 = request.getRequestDispatcher("/Farmacia/registro_producto.jsp");
                view2.forward(request,response);
                break;
            case "errorNeg":
                request.setAttribute("val","errorNeg");
                request.setAttribute("listDatos",datosProd);
                RequestDispatcher view4 = request.getRequestDispatcher("/Farmacia/registro_producto.jsp");
                view4.forward(request,response);
                break;
            case "muchoTxt":
                request.setAttribute("val","muchoTxt");
                request.setAttribute("listDatos",datosProd);
                RequestDispatcher view5 = request.getRequestDispatcher("/Farmacia/registro_producto.jsp");
                view5.forward(request,response);
                break;
            case "prodRepetido":
                request.setAttribute("val","prodRepetido");
                request.setAttribute("listDatos",datosProd);
                session.setAttribute("nombreprod",nom);
                RequestDispatcher view6 = request.getRequestDispatcher("/Farmacia/registro_producto.jsp");
                view6.forward(request,response);
                break;
            default:
                request.setAttribute("val","reg");
                request.setAttribute("listDatos",datosProd);
                RequestDispatcher view3 = request.getRequestDispatcher("/Farmacia/registro_producto.jsp");
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
        int idF = (Integer) session.getAttribute("idFarmacia");

        String nombre=request.getParameter("nombre").strip() != "" ? request.getParameter("nombre") : "";

        String stockStr=request.getParameter("stock").strip() != "" ? request.getParameter("stock") : "a";
        String stockTemp= stockStr.equals("a")? "0": request.getParameter("stock");
        String stockPosi= Integer.parseInt(stockTemp) >0 ? "": "neg";
        String precioUnitarioStr=request.getParameter("precioUnitario").strip() != "" ? request.getParameter("precioUnitario") : "a";
        String precioUnitarioTemp= precioUnitarioStr.equals("a")? "0": request.getParameter("precioUnitario");
        String precioPosi= Double.parseDouble(precioUnitarioTemp) >0 ? "": "neg";
        String descripcion=request.getParameter("descripcion").strip() != "" ? request.getParameter("descripcion") : "";
        String descripcionLeght=descripcion.length() >500 ? "muchoTxt": "";
        String recetamedica=request.getParameter("recetamedica").strip() != "" ? request.getParameter("recetamedica") : "a";

        Part part=request.getPart("foto");
        InputStream inputStream=part.getInputStream();

        if(nombre.equals("") || stockStr.equals("a") || precioUnitarioStr.equals("a") || descripcion.equals("") || recetamedica.equals("a")){
            response.sendRedirect(request.getContextPath() + "/Farm_Registro_Producto?msg=error&nom="+ nombre +"&des="+ descripcion +"&rec="+ recetamedica +"&sto="+stockStr+"&prec="+precioUnitarioStr);


        }else if( descripcionLeght.equals("muchoTxt")){
            response.sendRedirect(request.getContextPath() + "/Farm_Registro_Producto?msg=muchoTxt&nom="+ nombre +"&des="+ descripcion +"&rec="+ recetamedica +"&sto="+stockStr+"&prec="+precioUnitarioStr);


        }else if( stockPosi.equals("neg") || precioPosi.equals("neg")){
            response.sendRedirect(request.getContextPath() + "/Farm_Registro_Producto?msg=errorNeg&nom="+ nombre +"&des="+ descripcion +"&rec="+ recetamedica +"&sto="+stockStr+"&prec="+precioUnitarioStr);



        }

        else{
            int stock=Integer.parseInt(stockStr);
            double precioUnitario=Double.parseDouble(precioUnitarioStr);

            int verif= registrarProDao.existeProductoporfarmacia(idF,nombre);

            if( verif> 0 ){
                response.sendRedirect(request.getContextPath() + "/Farm_Registro_Producto?msg=prodRepetido&nom="+ nombre +"&des="+ descripcion +"&rec="+ recetamedica +"&sto="+stockStr+"&prec="+precioUnitarioStr);

            }else{

                int idproducto = registrarProDao.existeProducto(nombre);

                if(idproducto!=0){
                    registrarProDao.registrarProducto2(idF,idproducto,stock,precioUnitario,descripcion,inputStream,recetamedica);
                }else{
                    registrarProDao.registrarProducto(idF,nombre,stock,precioUnitario,descripcion,inputStream,recetamedica);
                }
                response.sendRedirect(request.getContextPath() + "/Farm_Registro_Producto?msg=ok");
            }


        }




    }
}
