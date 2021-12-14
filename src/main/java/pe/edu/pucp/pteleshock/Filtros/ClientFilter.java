package pe.edu.pucp.pteleshock.Filtros;

import pe.edu.pucp.pteleshock.Beans.BUsuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ClientFilter", urlPatterns = {"/Client_Bolsa_Compra", "/Client_Detalles_Producto", "/Client_Estado_Pendiente",
                                                    "/Client_Farmacias", "/Client_Listado_Producto", "/Client_Pedido",
                                                    "/Client_Perfil", "/Client_Productos_F"})
public class ClientFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        BUsuario cliente = (BUsuario) session.getAttribute("clienteSession");

        if (cliente == null) {
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        } else {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            chain.doFilter(request, response);
        }
    }
}
