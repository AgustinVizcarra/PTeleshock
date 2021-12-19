package pe.edu.pucp.pteleshock.Servlet;


import pe.edu.pucp.pteleshock.Beans.BPedidoD;
import pe.edu.pucp.pteleshock.Beans.BUsuario;
import pe.edu.pucp.pteleshock.Dao.GPedidoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Farm_Detalle_Pedido_ModServlet", value = "/Farm_Detalle_Pedido_Mod")
public class Farm_Detalles_Pedido_ModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");

        if (farmacia != null) {
            int idF = (Integer) session.getAttribute("idFarmacia");
            String pag= request.getParameter("pag")!= null? request.getParameter("pag"):"1";
            GPedidoDao gPedidoDao = new GPedidoDao();
            String idStr = request.getParameter("id");
            String msg = request.getParameter("msg") != null ? request.getParameter("msg") :"defect";
            String r = request.getParameter("r") != null ? request.getParameter("r") : "";
            int idint = Integer.parseInt(idStr);
            session.setAttribute("idpedido",idint);
            request.setAttribute("listaDPedido",gPedidoDao.listaPedidosD(idF,idint));
            request.setAttribute("listaproducto",gPedidoDao.listaProductospag(idF,idint,pag));
            session.setAttribute("listaprod",gPedidoDao.listaProductos(idF,idint));
            request.setAttribute("listaEstados",gPedidoDao.listaEstados());
            int cantPed=gPedidoDao.cantidadProductos1(idF,idint);
            String cantPedStr= String.valueOf(cantPed);
            request.setAttribute("cantPed",cantPedStr);
            request.setAttribute("msg",msg);
            //RequestDispatcher view = request.getRequestDispatcher("/Farmacia/detalles_pedido_modificado.jsp");
            //view.forward(request,response);
            //BPedido bPedido = gPedidoDao.obtenerPedidoId(idint);
            //System.out.println(bPedido.getIdPedido()+bPedido.getIdEstado());
            // if(bPedido!=null){
            // request.setAttribute("pedido",bPedido);

            //if(r.equals("1")){
               // RequestDispatcher view1=request.getRequestDispatcher("/Farmacia/detalles_pedido.jsp");
              //  view1.forward(request,response);
            //}else{
                RequestDispatcher view1=request.getRequestDispatcher("/Farmacia/detalles_pedido_modificado.jsp");
                view1.forward(request,response);
            //}
            // }else {
            //  response.sendRedirect(request.getContextPath() + "/Farm_Detalle_Pedido_Mod");
            //}

        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action") ==null? "editar" : request.getParameter("action");
        GPedidoDao gPedidoDao = new GPedidoDao();
        if(action.equals("editar")){
            String idEstado = request.getParameter("idEstado") != null ? request.getParameter("idEstado") : "0";
            String idPedido = request.getParameter("idPedido") != null ? request.getParameter("idPedido") : "0";
            System.out.println(idPedido+idEstado);
            int idEstadoInt = Integer.parseInt(idEstado);
            int idPedidoInt = Integer.parseInt(idPedido);

            String verifi=gPedidoDao.verificarestadopedido(idPedidoInt,idEstadoInt);
            if(verifi.equals("no")){

                if(idEstado.equals("4")){
                    gPedidoDao.actualizarestado(idEstadoInt,idPedidoInt);
                    ArrayList<BPedidoD> listaprod = (ArrayList<BPedidoD>) session.getAttribute("listaprod");
                    int idfarm= (int) session.getAttribute("idFarmacia");
                    gPedidoDao.actualizarStockCancelado(listaprod,idfarm);
                    response.sendRedirect(request.getContextPath() + "/Farm_Detalle_Pedido_Mod?id="+idPedido+"&msg=ok");
                }else {
                    if(gPedidoDao.validarpedidocancelado(idPedidoInt,4)){
                        response.sendRedirect(request.getContextPath() + "/Farm_Detalle_Pedido_Mod?id="+idPedido+"&msg=cancel");
                    }else{
                        gPedidoDao.actualizarestado(idEstadoInt,idPedidoInt);
                        response.sendRedirect(request.getContextPath() + "/Farm_Detalle_Pedido_Mod?id="+idPedido+"&msg=ok");
                    }
                }

            }else{
                response.sendRedirect(request.getContextPath() + "/Farm_Detalle_Pedido_Mod?id="+idPedido+"&msg="+verifi);
            }


        }
    }
}
