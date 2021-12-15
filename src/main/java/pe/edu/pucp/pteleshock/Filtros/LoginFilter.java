package pe.edu.pucp.pteleshock.Filtros;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import pe.edu.pucp.pteleshock.Dao.PaswordDao;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "LoginFilter",urlPatterns = {"/Login_Password_Recovery"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //System.out.println(request.getParameter("acc"));
        //String action = request.getParameter("acc")==null?"":request.getParameter("acc");
        String token = request.getParameter("correo")==null?"":request.getParameter("correo");
        HttpSession session = request.getSession();
        System.out.println("Valido: "+session.getAttribute("validacion"));
        //System.out.println("Primero paso por el filtro");
        if(!token.equalsIgnoreCase("")||(Integer) session.getAttribute("validacion")==1){
            //si alguno de los dos es distinto de nulo tenemos que ver cual es
            if(!token.equalsIgnoreCase("")){
                String key = "TeleshockToken";
                Algorithm algorithm = Algorithm.HMAC256(key);
                JWTVerifier verifier = JWT.require(algorithm).withIssuer("Teleshock").build();
                try{
                    verifier.verify(token);
                    System.out.println("¡La verificación pasó!");
                    try{
                        DecodedJWT originToken = JWT.decode(token);
                        System.out.println("El emisor obtenido por decodificación es:"+originToken.getIssuer());
                        System.out.println("El correo obtenido por decodificación es:"+originToken.getClaim("correo").asString());
                        request.setAttribute("correo",originToken.getClaim("correo").asString());
                        //aqui debo obtener el id
                        PaswordDao paswordDao = new PaswordDao();
                        int id = paswordDao.obtenerid(originToken.getClaim("correo").asString());
                        request.setAttribute("idusuario",String.valueOf(id));
                        RequestDispatcher view = request.getRequestDispatcher("/Login/recuperacion_contraseña.jsp");
                        session = request.getSession();
                        session.setAttribute("validacion",1);
                        view.forward(request,response);
                    } catch (JWTDecodeException e){
                        e.printStackTrace();
                        System.out.println("Ocurrio un error en la decodificacion del token");
                        RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
                        viewError.forward(request, response);
                    }
                } catch (JWTVerificationException e) {
                    e.printStackTrace();
                    System.out.println("¡Fallo en la verificación!");
                    RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
                    viewError.forward(request, response);
                }
            } else {
                //
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0);
                chain.doFilter(request, response);
                //flujo normal
            }
        }else{
            RequestDispatcher viewError = request.getRequestDispatcher("/Cliente/errorAccesoDenegado.jsp");
            viewError.forward(request, response);
        }
    }
}
