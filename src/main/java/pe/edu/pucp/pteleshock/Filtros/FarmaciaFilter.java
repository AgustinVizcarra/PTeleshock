package pe.edu.pucp.pteleshock.Filtros;

import pe.edu.pucp.pteleshock.Beans.BUsuario;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FarmaciaFilter",urlPatterns = {"/Farm_Detalles_Pedido_Mod_Final"
,"/Farm_Detalle_Pedido_Mod"
,"/Farm_Detalles_Pedido"
,"/Farm_Detalles_Producto"
,"/Farm_Editar_Inf_Producto"
,"/Farm_Gestionar_Pedidos"
,"/Farm_Index"
,"/Farm_Msje_Error_Editar_Producto"
,"/Farm_Msje_Error_Elim_Producto"
,"/Farm_Msje_Mal_Registro"
,"/Farm_Pefil"
,"/Farm_Registro_Producto"
,"/Farm_Vista_ProductosServlet"
,"/ImgServlet"
})
public class FarmaciaFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        BUsuario farmacia = (BUsuario) session.getAttribute("farmaciaSession");
        if (farmacia != null) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            chain.doFilter(request, response);

        } else {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }
}
